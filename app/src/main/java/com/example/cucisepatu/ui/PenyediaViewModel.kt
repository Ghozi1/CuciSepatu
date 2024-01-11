package com.example.cucisepatu.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cucisepatu.SepatuAplication
import com.example.cucisepatu.ui.JenisSepatu.JenisViewModel
import com.example.cucisepatu.ui.detail.DetailViewModel
import com.example.cucisepatu.ui.edit.EditViewModel
import com.example.cucisepatu.ui.home.HomeViewModel
import com.example.cucisepatu.ui.pemesanan.PemesananViewModel

fun CreationExtras.aplikasiSepatu(): SepatuAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SepatuAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            PemesananViewModel(aplikasiSepatu().container.cuciRepository)
        }

        initializer {
            HomeViewModel(aplikasiSepatu().container.cuciRepository)
        }

        initializer {
            JenisViewModel(aplikasiSepatu().container.cuciRepository)
        }

        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiSepatu().container.cuciRepository
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSepatu().container.cuciRepository
            )
        }
    }
}