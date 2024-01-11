package com.example.cucisepatu.ui.pemesanan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.ui.PemesananEvent
import com.example.cucisepatu.ui.PemesananJenis
import com.example.cucisepatu.ui.toSepatu

class PemesananViewModel (private val cuciRepository: CuciRepository) : ViewModel() {

    var pemesananUIState by mutableStateOf(PemesananJenis())
        private set

    fun updatePesananUIState(pemesananEvent: PemesananEvent) {
        pemesananUIState = PemesananJenis(pemesananEvent = pemesananEvent)
    }

    suspend fun addPesanan() {
        cuciRepository.save(pemesananUIState.pemesananEvent.toSepatu())
    }
}