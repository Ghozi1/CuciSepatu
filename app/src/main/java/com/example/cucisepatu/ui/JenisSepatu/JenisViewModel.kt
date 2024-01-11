package com.example.cucisepatu.ui.JenisSepatu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cucisepatu.data.JenisSepatuRepository

class JenisViewModel(private val jenisSepatuRepository: JenisSepatuRepository) : ViewModel() {

    var jenisUIState by mutableStateOf(JenisUIState())
        private set

    fun updateJenisUIState(jenisEvent: JenisEvent) {
        jenisUIState = JenisUIState(jenisEvent = jenisEvent)
    }

    suspend fun addJenis() {
        jenisSepatuRepository.savejenis(jenisUIState.jenisEvent.toJenis_Sepatu())
    }
}
