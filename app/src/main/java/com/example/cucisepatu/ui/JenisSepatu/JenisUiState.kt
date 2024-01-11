package com.example.cucisepatu.ui.JenisSepatu

import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.model.Sepatu


data class JenisUiState(
    val pemesananJenisEvent: PemesananJenisEvent = PemesananJenisEvent()
)

data class PemesananJenisEvent(
    val id: String ="",
    val nama: String = ""
)

/*Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun PemesananJenisEvent.toJenis_Sepatu() = Jenis_Sepatu(
    id = id,
    nama = nama
)

data class DetailJenisUIState(
    val pemesananJenisEvent: PemesananJenisEvent = PemesananJenisEvent()
)

fun Sepatu.toJenisUiStateSepatu(): JenisUiState= JenisUiState(
    pemesananJenisEvent = this.toJenisDetailSepatu(),
)

fun Sepatu.toJenisDetailSepatu(): PemesananJenisEvent = PemesananJenisEvent(
    id = id,
    nama = nama
)

data class JenisUIState(
    val listJenis_Sepatu: List<Jenis_Sepatu> = listOf(),
    val dataLength: Int = 0
)

