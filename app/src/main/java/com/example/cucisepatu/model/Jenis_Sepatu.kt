package com.example.cucisepatu.model

import androidx.room.PrimaryKey

data class Jenis_Sepatu(
    @PrimaryKey
    val id: String,
    val nama: String
) {
    // Konstruktor tambahan
    constructor(): this("","",)
}
