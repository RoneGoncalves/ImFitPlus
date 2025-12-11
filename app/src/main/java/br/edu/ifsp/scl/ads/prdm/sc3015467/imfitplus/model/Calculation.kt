package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "calculation",
    foreignKeys = [
        ForeignKey(
            entity = PersonalData::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(index = true)
    var userId: Int = 0,

    var imc: Float = 0f,
    var category: String = "",
    var tmb: Float = 0f,
    var idealWeight: Float = 0f
) : Parcelable
