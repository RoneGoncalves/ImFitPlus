package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.db

import androidx.room.*

@Dao
interface PersonalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PersonalDataEntity)

    @Query("SELECT * FROM personal_data ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): PersonalDataEntity?

    @Query("SELECT * FROM personal_data")
    suspend fun getAll(): List<PersonalDataEntity>

    @Delete
    suspend fun delete(data: PersonalDataEntity)
}
