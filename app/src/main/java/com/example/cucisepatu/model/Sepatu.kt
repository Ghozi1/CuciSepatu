package com.example.cucisepatu.model

data class Sepatu(
    val id: String,
    val nama: String,
    val nohp: String,
    val alamat : String,
    val jenisSepatu: String,
    val tipeCuci: String
) {
    // Konstruktor tambahan
    constructor(): this("","","","","","")
}
