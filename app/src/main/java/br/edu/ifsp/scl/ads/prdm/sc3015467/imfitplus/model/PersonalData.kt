package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "personal_data")
@Parcelize
data class PersonalData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var name: String = "",
    var age: Int = 0,
    var sex: String = "",
    var height: Float = 0f,
    var weight: Float = 0f,
    var activityLevel: String = "",
    var imc: Float = 0f
) : Parcelable

