package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityCaloricExpenditureBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.DAILY_EXPENDITURE
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.PERSONAL_DATA
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.TMB

class CaloricExpenditureActivity : AppCompatActivity() {
    private  val aceb: ActivityCaloricExpenditureBinding by lazy {
        ActivityCaloricExpenditureBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aceb.root)

        val personalData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PERSONAL_DATA, PersonalData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PERSONAL_DATA)
        }

        val imcCategory = intent.getStringExtra(IMC_CATEGORY)

        val tmb = personalData?.let { calculateTMB(it) }
        val activityFactor = personalData?.let { getActivityFactor(it.activityLevel) }
        val dailyExpenditure = activityFactor?.let { tmb?.times(it) }

        personalData?.let {
            with(aceb) {
                tmbNameTv.text = personalData.name
                tmbTv.text = String.format("TMB: %.2f", tmb)
                dailyExpenditureTv.text = String.format("Gasto calórico diário: %.2f", dailyExpenditure)
            }
        }

        aceb.calculateIdealWeightBt.setOnClickListener {
            val intent = Intent(this, IdealWeightActivity::class.java)
            intent.putExtra(PERSONAL_DATA, personalData)
            intent.putExtra(IMC_CATEGORY, imcCategory)
            intent.putExtra(TMB, String.format("TMB: %.2f", tmb))
            intent.putExtra(DAILY_EXPENDITURE, String.format("Gasto calórico diário: %.2f", dailyExpenditure))
            startActivity(intent)
        }

        aceb.backBt.setOnClickListener {
            finish()
        }
    }

    fun calculateTMB(data: PersonalData): Double {
        return if (data.sex == "Masculino") {
            66 + (13.7 * data.weight) + (5 * data.height * 100) - (6.8 * data.age)
        } else {
            655 + (9.6 * data.weight) + (1.8 * data.height * 100) - (4.7 * data.age)
        }
    }

    fun getActivityFactor(level: String): Double {
        return when (level.lowercase()) {
            "sedentário" -> 1.2
            "leve" -> 1.375
            "moderado" -> 1.55
            "intenso" -> 1.725
            else -> 1.2
        }
    }
}