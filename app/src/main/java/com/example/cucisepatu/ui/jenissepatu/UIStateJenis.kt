package com.example.cucisepatu.ui.jenissepatu

import com.example.cucisepatu.model.Jenis_Sepatu

data class PemesananJenisUIState(
    val pemesananJenisEvent: PemesananJenis = PemesananJenis()
)

data class PemesananJenis(
    val id: String = "", // Change the type to Int
    val nama: String = "",
)

fun PemesananJenis.toJenis_Sepatu(): Jenis_Sepatu = Jenis_Sepatu(
    id = id,
    nama = nama,
)

data class DetailJenisUIState(
    val pemesananJenis: PemesananJenis = PemesananJenis()
)

fun Jenis_Sepatu.toUiStateJenisSepatu(): PemesananJenisUIState = PemesananJenisUIState(
    pemesananJenisEvent = this.toDetailJenisSepatu(),
)

fun Jenis_Sepatu.toDetailJenisSepatu(): PemesananJenis = PemesananJenis(
    id = id,
    nama = nama,
)

data class JenisUIState(
    val listSepatu: List<Jenis_Sepatu> = listOf(),
    val dataLength: Int = 0
)
