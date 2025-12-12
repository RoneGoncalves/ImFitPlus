package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.adapter.CalculationHistoryAdapter
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.ImFitPlusDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityCalculationHistoryBinding
import kotlinx.coroutines.withContext

class CalculationHistoryActivity : AppCompatActivity() {

    private val achb: ActivityCalculationHistoryBinding by lazy {
        ActivityCalculationHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(achb.root)

        loadCalculations()
    }

    private fun loadCalculations() {
        lifecycleScope.launch {
            val db = ImFitPlusDatabase.getDatabase(this@CalculationHistoryActivity)

            val history = withContext(Dispatchers.IO) {
                db.calculationDao().getCalculationHistory()
            }

            achb.calculationHistoryLv.adapter = CalculationHistoryAdapter(this@CalculationHistoryActivity, history.toMutableList())
        }
    }
}
