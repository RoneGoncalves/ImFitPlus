package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityPersonalDataBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.ImFitPlusDatabase
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.utils.ConstantsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var db: ImFitPlusDatabase

    private val apdb: ActivityPersonalDataBinding by lazy {
        ActivityPersonalDataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apdb.root)

        db = ImFitPlusDatabase.getDatabase(this)

        // Carrega os dados salvos, se existirem
        loadPersonalData()

        with(apdb) {

            calculateBt.setOnClickListener {

                if (!validateInputs()) return@setOnClickListener

                val personalData = PersonalData(
                    name = nameEt.text.toString().trim(),
                    age = ageEt.text.toString().toInt(),
                    sex = when (sexRg.checkedRadioButtonId) {
                        R.id.female_rb -> "Feminino"
                        R.id.male_rb -> "Masculino"
                        else -> ""
                    },
                    height = heightEt.text.toString().toFloat(),
                    weight = weightEt.text.toString().toFloat(),
                    activityLevel = activityLevelSp.selectedItem.toString(),
                    imc = 0f // IMC NÃO SERÁ PERSISTIDO AQUI
                )

                // Persiste os dados antes de seguir
                savePersonalData(personalData)

                // Calcula IMC só para próxima tela
                val imc = personalData.weight /
                        (personalData.height * personalData.height)

                val dataToSend = personalData.copy(imc = imc)

                val intent = Intent(
                    this@PersonalDataActivity,
                    ImcResultActivity::class.java
                )
                intent.putExtra(ConstantsUtils.PERSONAL_DATA, dataToSend)
                startActivity(intent)
            }
        }
    }

    private fun loadPersonalData() {
        CoroutineScope(Dispatchers.IO).launch {
            val saved = db.personalDataDao().getPersonalData()

            saved?.let {
                withContext(Dispatchers.Main) {
                    apdb.nameEt.setText(it.name)
                    apdb.ageEt.setText(it.age.toString())
                    apdb.heightEt.setText(it.height.toString())
                    apdb.weightEt.setText(it.weight.toString())

                    // Seleciona sexo
                    when (it.sex) {
                        "Feminino" -> apdb.femaleRb.isChecked = true
                        "Masculino" -> apdb.maleRb.isChecked = true
                    }

                    // Seleciona nível de atividade
                    val pos = resources.getStringArray(R.array.activity_level)
                        .indexOf(it.activityLevel)

                    if (pos >= 0) apdb.activityLevelSp.setSelection(pos)
                }
            }
        }
    }

    private fun savePersonalData(personalData: PersonalData) {
        CoroutineScope(Dispatchers.IO).launch {
            db.personalDataDao().save(personalData)
        }
    }

    private fun validateInputs(): Boolean {
        with(apdb) {
            val name = nameEt.text.toString().trim()
            if (name.isEmpty()) {
                nameEt.error = "Informe o nome"
                return false
            }

            val age = ageEt.text.toString().toIntOrNull()
            if (age == null || age !in 1..120) {
                ageEt.error = "Idade inválida"
                return false
            }

            val height = heightEt.text.toString().toFloatOrNull()
            if (height == null || height <= 0f) {
                heightEt.error = "Altura inválida"
                return false
            }

            val weight = weightEt.text.toString().toFloatOrNull()
            if (weight == null || weight <= 0f) {
                weightEt.error = "Peso inválido"
                return false
            }

            return true
        }
    }
}
