package com.example.cucisepatu.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cucisepatu.data.CuciRepository
import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.ui.DetailCuci
import com.example.cucisepatu.ui.UIStateCuci
import com.example.cucisepatu.ui.toSepatu
import kotlinx.coroutines.launch


class HomeViewModel(private val repositoriCuci: CuciRepository) : ViewModel() {

    var uiStateKontak by mutableStateOf(UIStateCuci())
        private set

    var jenisSepatuItems by mutableStateOf<List<Jenis_Sepatu>>(emptyList())
        private set

    init {
        // Fetch jenisSepatuItems when the ViewModel is created
        viewModelScope.launch {
            jenisSepatuItems = repositoriCuci.getJenisSepatuItems()
        }
    }

    fun updateUiState(detailKontak: DetailCuci) {
        uiStateKontak = UIStateCuci(detailCuci = detailKontak)
    }

    suspend fun saveKontak() {
        repositoriCuci.save(uiStateKontak.detailCuci.toSepatu())
    }
}