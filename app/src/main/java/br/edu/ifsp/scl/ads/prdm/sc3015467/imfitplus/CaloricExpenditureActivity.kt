package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityCaloricExpenditureBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData

class CaloricExpenditureActivity : AppCompatActivity() {
    private  val ceab: ActivityCaloricExpenditureBinding by lazy {
        ActivityCaloricExpenditureBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ceab.root)

        val personalData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("personalData", PersonalData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("personalData")
        }

        personalData?.let {
            val tmb = calculateTMB(personalData)
            val activityFactor = getActivityFactor(personalData.activityLevel)
            val dailyExpenditure = tmb * activityFactor

            with(ceab) {
                tmbNameTv.text = personalData.name
                tmbTv.text = String.format("TMB: %.2f", tmb)
                dailyExpenditureTv.text = String.format("Gasto calórico diário: %.2f", dailyExpenditure)

            }
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