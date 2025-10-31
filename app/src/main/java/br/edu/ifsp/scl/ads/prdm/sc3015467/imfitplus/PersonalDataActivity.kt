package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityPersonalDataBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.Constants

class PersonalDataActivity : AppCompatActivity() {
    private val apdb: ActivityPersonalDataBinding by lazy {
        ActivityPersonalDataBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apdb.root)

        with(apdb) {
            calculateBt.setOnClickListener {
                if (!validateInputs()) return@setOnClickListener
                val name = nameEt.text.toString().trim()
                val age = ageEt.text.toString().toInt()
                val height = heightEt.text.toString().toFloat()
                val weight = weightEt.text.toString().toFloat()
                val sex = when (sexRg.checkedRadioButtonId) {
                    R.id.female_rb -> "Feminino"
                    R.id.male_rb -> "Masculino"
                    else -> ""
                }
                val activityLevel = activityLevelSp.selectedItem.toString()

                val imc = weight / (height * height)

                val personalData = PersonalData(
                    name = name,
                    age = age,
                    sex = sex,
                    height = height,
                    weight = weight,
                    activityLevel = activityLevel,
                    imc = imc
                )

                val intent = Intent(this@PersonalDataActivity, ImcResultActivity::class.java)
                intent.putExtra(Constants.PERSONAL_DATA, personalData)
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(): Boolean {
        with(apdb) {

            val name = nameEt.text.toString().trim()
            if (name.isEmpty()) {
                nameEt.error = "Informe o nome"
                nameEt.requestFocus()
                Toast.makeText(this@PersonalDataActivity, "Por favor, informe o nome.", Toast.LENGTH_SHORT).show()
                return false
            }

            val ageStr = ageEt.text.toString().trim()
            val age = ageStr.toIntOrNull()
            if (ageStr.isEmpty() || age == null) {
                ageEt.error = "Idade inválida"
                ageEt.requestFocus()
                Toast.makeText(this@PersonalDataActivity, "Informe uma idade válida.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (age !in 1..120) {
                ageEt.error = "Idade deve estar entre 1 e 120"
                ageEt.requestFocus()
                Toast.makeText(this@PersonalDataActivity, "Idade fora do intervalo esperado (1-120).", Toast.LENGTH_SHORT).show()
                return false
            }

            val height = heightEt.text.toString().toFloatOrNull()
            if (height == null || height <= 0f) {
                heightEt.error = "Altura inválida"
                heightEt.requestFocus()
                Toast.makeText(this@PersonalDataActivity, "Informe uma altura válida (> 0).", Toast.LENGTH_SHORT).show()
                return false
            }

            val weight = weightEt.text.toString().toFloatOrNull()
            if (weight == null || weight <= 0f) {
                weightEt.error = "Peso inválido"
                weightEt.requestFocus()
                Toast.makeText(this@PersonalDataActivity, "Informe um peso válido (> 0).", Toast.LENGTH_SHORT).show()
                return false
            }

            return true
        }
    }

}