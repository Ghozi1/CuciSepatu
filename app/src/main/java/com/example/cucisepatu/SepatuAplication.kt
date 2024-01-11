package com.example.cucisepatu

import android.app.Application
import com.example.cucisepatu.data.CuciFireBase
import com.example.cucisepatu.data.CuciSepatuFireBase

class SepatuAplication : Application() {
    lateinit var container: CuciSepatuFireBase

    override fun onCreate(){
        super.onCreate()

        container = CuciFireBase()
    }
}