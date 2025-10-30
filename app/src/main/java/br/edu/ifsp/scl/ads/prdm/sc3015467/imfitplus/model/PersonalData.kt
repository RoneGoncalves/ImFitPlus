package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalData(
    val name: String,
    val age: Int,
    val sex: String,
    val height: Float,
    val weight: Float,
    val activityLevel: String,
    val imc: Float
) : Parcelable
