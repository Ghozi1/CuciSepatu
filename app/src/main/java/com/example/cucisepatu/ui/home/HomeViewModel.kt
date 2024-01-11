package com.example.cucisepatu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PesananUIState {
    data class Success(val kontak: Flow<List<Sepatu>>) : PesananUIState()
    object Error : PesananUIState()
    object Loading : PesananUIState()
}

class HomeViewModel(private val cuciRepository: CuciRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = cuciRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listSepatu = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )
}
