package com.example.cucisepatu.ui.JenisSepatu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.data.JenisSepatuRepository
import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


sealed class PemesananUIState {
    data class Success(val Jenis_Sepatu: Flow<List<Jenis_Sepatu>>) : PemesananUIState()
    object Error : PemesananUIState()
    object Loading : PemesananUIState()
}

class JenisViewModel(private val jenisSepatuRepository: JenisSepatuRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val jenisUIState: StateFlow<JenisUIState> = jenisSepatuRepository.getAlljenis()
        .filterNotNull()
        .map {
            JenisUIState (listJenis_Sepatu = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = JenisUIState()

        )

}
