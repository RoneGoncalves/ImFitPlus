package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityImcResultBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData

class ImcResultActivity : AppCompatActivity() {
    private val airb: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(airb.root)

        val personalData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("personalData", PersonalData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("personalData")
        }

        personalData?.let {
            val formatedImc = String.format("IMC: %.2f", personalData.imc)
            val category = getImcCategory(personalData.imc)

            with(airb) {
                nameTv.text = personalData.name
                imcTv.text = formatedImc
                categoryTv.text = category
            }
        }

        airb.calculateCalorieExpenditureBt.setOnClickListener {
            val intent = Intent(this, CaloricExpenditureActivity::class.java)
                intent.putExtra("personalData", personalData)
                startActivity(intent)
        }

        airb.backBt.setOnClickListener {
            startActivity(Intent(this, PersonalDataActivity::class.java))
        }
    }
    fun getImcCategory(imc: Float): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}