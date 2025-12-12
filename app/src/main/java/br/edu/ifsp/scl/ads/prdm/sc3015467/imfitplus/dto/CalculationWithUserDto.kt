package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.dto

data class CalculationWithUserDto(
    val userName: String,
    val age: Int,
    val sex: String,
    val imc: Float,
    val category: String,
    val tmb: Float,
    val idealWeight: Float
)
