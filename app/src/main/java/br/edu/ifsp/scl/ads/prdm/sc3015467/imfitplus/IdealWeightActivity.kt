package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.controller.HistoryController
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityIdealWeightBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.Calculation
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.DAILY_EXPENDITURE
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.IDEAL_WEIGHT
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.PERSONAL_DATA
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.TMB
import kotlinx.coroutines.launch
import kotlin.math.abs

class IdealWeightActivity : AppCompatActivity() {
    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aiwb.root)

        val personalData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PERSONAL_DATA, PersonalData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PERSONAL_DATA)
        }

        val imcCategory = intent.getStringExtra(IMC_CATEGORY)
        val tmb = intent.getStringExtra(TMB)
        val dailyExpenditure = intent.getStringExtra(DAILY_EXPENDITURE)

        val idealWeight = personalData?.let { calculateIdealWeight(it.height) }
        val weightDifference = personalData?.let { idealWeight?.let { it1 -> calculateWeightDifference(it.weight, it1) } }

        personalData?.let {
            with(aiwb) {
                nameTv.text = it.name
                idealWeightTv.text = String.format("Peso ideal: %.2f", idealWeight)
                weightDifferenceTv.text = String.format("Diferença: %.2f", weightDifference)
            }

            val calculation = Calculation(
                userId = it.id ?: 0,
                imc = it.imc,
                category = imcCategory ?: "",
                tmb = tmb?.toFloatOrNull() ?: 0f,
                idealWeight = idealWeight ?: 0f
            )

            lifecycleScope.launch {
                HistoryController(this@IdealWeightActivity).saveCalculation(calculation)
            }
        }

        aiwb.imcReportBt.setOnClickListener {
            val intent = Intent(this, HealthReportActivity::class.java)
            intent.putExtra(PERSONAL_DATA, personalData)
            intent.putExtra(IMC_CATEGORY, imcCategory)
            intent.putExtra(TMB, tmb)
            intent.putExtra(DAILY_EXPENDITURE, dailyExpenditure)
            intent.putExtra(IDEAL_WEIGHT, String.format("Peso ideal: %.2f", idealWeight))
            startActivity(intent)
        }

        aiwb.backBt.setOnClickListener {
            finish()
        }
    }

    fun calculateIdealWeight(height: Float): Float {
        val idealWeight = 22 * (height * height);
        return idealWeight
    }

    fun calculateWeightDifference(weight: Float, idealWeight: Float): Float {
        val weightDifference = abs(weight - idealWeight);
        return weightDifference
    }
}