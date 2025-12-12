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

    suspend fun updateCalculation(calc: Calculation): Int = withContext(Dispatchers.IO) {
        calculationDao.update(calc)
    }

    suspend fun deleteCalculation(calc: Calculation): Int = withContext(Dispatchers.IO) {
        calculationDao.delete(calc)
    }

    suspend fun getCalculationById(id: Int): Calculation? = withContext(Dispatchers.IO) {
        calculationDao.getById(id)
    }

    suspend fun getCalculationsByUser(userId: Int): List<Calculation> = withContext(Dispatchers.IO) {
        calculationDao.getByUser(userId)
    }

    suspend fun getAllCalculations(): List<Calculation> = withContext(Dispatchers.IO) {
        calculationDao.getAll()
    }
}
