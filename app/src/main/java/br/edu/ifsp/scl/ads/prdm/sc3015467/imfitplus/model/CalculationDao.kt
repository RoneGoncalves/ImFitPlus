package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import androidx.room.*

@Dao
interface CalculationDao {

    @Insert
    fun insert(calculation: Calculation): Long

    @Update
    fun update(calculation: Calculation): Int

    @Delete
    fun delete(calculation: Calculation): Int

    @Query("SELECT * FROM calculation WHERE id = :id")
    fun getById(id: Int): Calculation?

    @Query("SELECT * FROM calculation WHERE userId = :userId")
    fun getByUser(userId: Int): List<Calculation>

    @Query("SELECT * FROM calculation")
    fun getAll(): List<Calculation>
}

