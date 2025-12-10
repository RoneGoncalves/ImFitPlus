package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal_data")
data class PersonalDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val sex: String,
    val height: Float,
    val weight: Float,
    val activityLevel: String,
    val imc: Float
)
