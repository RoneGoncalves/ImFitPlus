package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.ImFitPlusDatabase
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.PersonalData

class MainController(context: Context) {

    private val personalDataDao = ImFitPlusDatabase.getDatabase(context).personalDataDao()

    suspend fun savePersonalData(data: PersonalData) {
        personalDataDao.save(data)
    }

    suspend fun loadPersonalData(): PersonalData? {
        return personalDataDao.getPersonalData()
    }
}
