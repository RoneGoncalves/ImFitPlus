package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.Calculation
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.ImFitPlusDatabase

class HistoryController(context: Context) {

    private val calculationDao = ImFitPlusDatabase.getDatabase(context).calculationDao()

    fun saveCalculation(calc: Calculation): Long {
        return calculationDao.insert(calc)
    }

    fun updateCalculation(calc: Calculation): Int {
        return calculationDao.update(calc)
    }

    fun deleteCalculation(calc: Calculation): Int {
        return calculationDao.delete(calc)
    }

    fun getCalculationById(id: Int): Calculation? {
        return calculationDao.getById(id)
    }

    fun getCalculationsByUser(userId: Int): List<Calculation> {
        return calculationDao.getByUser(userId)
    }

    fun getAllCalculations(): List<Calculation> {
        return calculationDao.getAll()
    }
}
