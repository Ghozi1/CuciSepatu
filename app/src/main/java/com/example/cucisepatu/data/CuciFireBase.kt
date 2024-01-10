package com.example.cucisepatu.data

import com.google.firebase.firestore.FirebaseFirestore

interface CuciSepatuFireBase {
    val cuciRepository : CuciRepository
}

class CuciFireBase : CuciSepatuFireBase {
    private val firestore :FirebaseFirestore = FirebaseFirestore.getInstance()

    override val cuciRepository: CuciRepository by lazy {
        CuciRepositoryImpl(firestore)
    }
}

