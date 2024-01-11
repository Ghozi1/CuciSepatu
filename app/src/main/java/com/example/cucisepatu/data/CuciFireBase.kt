package com.example.cucisepatu.data

import com.google.firebase.firestore.FirebaseFirestore

interface CuciSepatuFireBase {
    val cuciRepository : CuciRepository
    val jenisSepatuRepository : JenisSepatuRepository
}

class CuciFireBase : CuciSepatuFireBase {
    private val firestore :FirebaseFirestore = FirebaseFirestore.getInstance()

    override val cuciRepository: CuciRepository by lazy {
        CuciRepositoryImpl(firestore)
    }

    override val jenisSepatuRepository: JenisSepatuRepository by lazy {
        JenisRepositoryImpl(firestore)
    }
}

