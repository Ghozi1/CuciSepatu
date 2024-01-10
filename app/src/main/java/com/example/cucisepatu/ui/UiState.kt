package com.example.cucisepatu.ui

import com.example.cucisepatu.model.Sepatu


data class UIStateCuci(
    val detailCuci: DetailCuci = DetailCuci(),

    )

data class DetailCuci(
    val id: Int = 0,
    val nama: String = "",
    val nohp: String = "",
    val alamat: String = "",
    val jenisSepatu : Int = 0,
    val tipeCuci : String = ""
)

/*Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailCuci.toSepatu(): Sepatu = Sepatu(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    jenisSepatu = jenisSepatu,
    tipeCuci = tipeCuci
)

fun Sepatu.toUiStateSepatu(isEntryValid: Boolean =false): UIStateCuci= UIStateCuci(
    detailCuci = this.toDetailCuci(),
)

fun Sepatu.toDetailCuci(): DetailCuci = DetailCuci(
    id = id,
    nama = nama ,
    alamat = alamat,
    nohp = nohp,
    jenisSepatu = jenisSepatu,
    tipeCuci = tipeCuci
)

