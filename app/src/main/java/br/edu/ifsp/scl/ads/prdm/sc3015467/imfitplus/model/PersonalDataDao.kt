package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PersonalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(personalData: PersonalData): Long

    @Query("SELECT * FROM personal_data LIMIT 1")
    suspend fun getPersonalData(): PersonalData?
}
