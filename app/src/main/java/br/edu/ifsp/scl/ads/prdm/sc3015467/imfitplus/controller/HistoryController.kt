package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.Calculation
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.ImFitPlusDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryController(context: Context) {

    private val calculationDao = ImFitPlusDatabase.getDatabase(context).calculationDao()

    suspend fun saveCalculation(calc: Calculation): Long = withContext(Dispatchers.IO) {
        calculationDao.insert(calc)
    }

    suspend fun getCalculationHistory() = withContext(Dispatchers.IO) {
        calculationDao.getCalculationHistory()
    }
}
