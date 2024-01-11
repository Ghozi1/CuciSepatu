package com.example.cucisepatu.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.ui.DetailUIState
import com.example.cucisepatu.ui.toDetailSepatu
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: CuciRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val sepatuId: String = checkNotNull(savedStateHandle[DetailDestination.sepatuId])

    val uiState: StateFlow<DetailUIState> =
        repository.getSepatuById(sepatuId)
            .filterNotNull()
            .map {
                DetailUIState(pemesananEvent = it.toDetailSepatu())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )
    suspend fun deletePesanan() {
        repository.delete(sepatuId)

    }
}