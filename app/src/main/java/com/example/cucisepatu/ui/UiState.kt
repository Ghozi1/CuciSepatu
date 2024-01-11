package com.example.cucisepatu.ui

import com.example.cucisepatu.model.Sepatu


data class PemesananUIState(
    val pemesananEvent: PemesananEvent = PemesananEvent()
)

data class PemesananEvent(
    val id: String ="",
    val nama: String = "",
    val nohp: String = "",
    val alamat: String = "",
    val jenisSepatu : String = "",
    val tipeCuci : String = ""
)

/*Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun PemesananEvent.toSepatu(): Sepatu = Sepatu(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    jenisSepatu = jenisSepatu,
    tipeCuci = tipeCuci
)

data class DetailUIState(
    val pemesananEvent: PemesananEvent = PemesananEvent()
)

fun Sepatu.toUiStateSepatu(): PemesananUIState= PemesananUIState(
    pemesananEvent = this.toDetailSepatu(),
)

fun Sepatu.toDetailSepatu(): PemesananEvent = PemesananEvent(
    id = id,
    nama = nama ,
    alamat = alamat,
    nohp = nohp,
    jenisSepatu = jenisSepatu,
    tipeCuci = tipeCuci
)

data class HomeUIState(
    val listSepatu: List<Sepatu> = listOf(),
    val dataLength: Int = 0
)