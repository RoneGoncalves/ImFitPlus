package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityHealthReportBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.DAILY_EXPENDITURE
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.IDEAL_WEIGHT
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils.TMB

class HealthReportActivity : AppCompatActivity() {
    private val ahrb: ActivityHealthReportBinding by lazy { 
        ActivityHealthReportBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahrb.root)

        val personalData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ConstantsUtils.PERSONAL_DATA, PersonalData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(ConstantsUtils.PERSONAL_DATA)
        }
        val imcCategory = intent.getStringExtra(IMC_CATEGORY)
        val tmb = intent.getStringExtra(TMB)
        val idealWeight = intent.getStringExtra(IDEAL_WEIGHT)
        val dailyExpenditure = intent.getStringExtra(DAILY_EXPENDITURE)

        personalData?.let {
            with(ahrb) {
                reportNameTv.text = personalData.name
                reportImcTv.text = personalData.imc.toString()
            }
        }
        ahrb.reportCategoryTv.text = "Categoria: ${imcCategory}"
        ahrb.reportTmbTv.text = tmb
        ahrb.reportIdealWeightTv.text = idealWeight
        ahrb.reportDailyExpenditureTv.text = dailyExpenditure
        ahrb.reportDrinkWaterTv.text = getString(R.string.drink_water)
    }
}