package com.example.cucisepatu.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cucisepatu.ui.SepatuAplication
import com.example.cucisepatu.ui.screen.HomeViewModel


fun CreationExtras.aplikasiCuci(): SepatuAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as  SepatuAplication)


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiCuci().container.cuciRepository)
        }
    }
}