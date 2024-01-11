package com.example.cucisepatu.model

data class Jenis_Sepatu(
    val id: String,
    val nama: String
) {
    // Konstruktor tambahan
    constructor(): this("","",)
}
