package com.example.cucisepatu.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.ui.PemesananEvent
import com.example.cucisepatu.ui.PemesananUIState
import com.example.cucisepatu.ui.toSepatu
import com.example.cucisepatu.ui.toUiStateSepatu
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: CuciRepository
) : ViewModel() {

    var sepatuUiState by mutableStateOf(PemesananUIState())
        private set

    private val sepatuId: String = checkNotNull(savedStateHandle[EditDestination.sepatuId])

    init {
        viewModelScope.launch {
            sepatuUiState =
                repository.getSepatuById(sepatuId)
                    .filterNotNull()
                    .first()
                    .toUiStateSepatu()
        }
    }

    fun updateUIState(pemesananEvent: PemesananEvent) {
        sepatuUiState = sepatuUiState.copy(pemesananEvent = pemesananEvent)
    }

    suspend fun updateSepatu() {
        repository.update(sepatuUiState.pemesananEvent.toSepatu())

    }
}