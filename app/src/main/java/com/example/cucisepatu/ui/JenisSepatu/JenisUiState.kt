package com.example.cucisepatu.ui.JenisSepatu

import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.model.Sepatu


data class JenisUIState(
    val jenisEvent: JenisEvent = JenisEvent()
)

data class JenisEvent(
    val id: String ="",
    val nama: String = ""
)

/*Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun JenisEvent.toJenis_Sepatu() = Jenis_Sepatu(
    id = id,
    nama = nama
)
fun Jenis_Sepatu.toJenisUiStateSepatu(): JenisUIState= JenisUIState(
    jenisEvent = this.toJenisDetailSepatu(),
)

fun Jenis_Sepatu.toJenisDetailSepatu(): JenisEvent = JenisEvent(
    id = id,
    nama = nama
)


