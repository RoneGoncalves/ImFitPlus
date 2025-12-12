package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import androidx.room.*
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.dto.CalculationWithUserDto

@Dao
interface CalculationDao {

    @Insert
    fun insert(calculation: Calculation): Long

    @Query("""
    SELECT 
        p.name AS userName,
        p.age AS age,
        p.sex AS sex,
        c.imc,
        c.category,
        c.tmb,
        c.idealWeight
    FROM calculation c
    INNER JOIN personal_data p ON p.id = c.userId
""")
    fun getCalculationHistory(): List<CalculationWithUserDto>
}

