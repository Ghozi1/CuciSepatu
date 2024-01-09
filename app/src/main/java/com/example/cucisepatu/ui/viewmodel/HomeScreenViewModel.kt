package com.example.cucisepatu.ui.viewmodel

import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {

    // Properti ViewModel
    private var password: String = ""

    // Metode untuk mengatur password
    fun setPassword(newPassword: String) {
        password = newPassword
    }

    // Metode untuk memeriksa password
    fun isPasswordCorrect(enteredPassword: String): Boolean {
        return password == enteredPassword
    }
}
