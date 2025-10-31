package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityIdealWeightBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.PERSONAL_DATA
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

        personalData?.let {
            val idealWeight = calculateIdealWeight(personalData.height)
            val weightDifference = calculateWeightDifference(personalData.weight, idealWeight)

            with(aiwb) {
                nameTv.text = personalData.name
                idealWeightTv.text = String.format("Peso ideal: %.2f", personalData.weight)
                weightDifferenceTv.text = String.format("Diferença: %.2f", weightDifference)
            }
        }

        aiwb.backBt.setOnClickListener {
            finish()
        }

        aiwb.finishAppBt.setOnClickListener {
            finishAffinity()
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