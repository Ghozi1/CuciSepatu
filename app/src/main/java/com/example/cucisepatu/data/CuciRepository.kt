package com.example.cucisepatu.data

import com.example.cucisepatu.model.Sepatu
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

interface CuciRepository{
    fun getAll() : Flow<List<Sepatu>>
    suspend fun save(sepatu: Sepatu) : String
    suspend fun update (sepatu: Sepatu)
    suspend fun delete (sepatuId: String)
    fun getSepatuById(sepatuId: String) : Flow<Sepatu>
}

class CuciSepatuRepository(private val firestore: FirebaseFirestore) : CuciRepository{
    override fun getAll(): Flow<List<Sepatu>> {
        TODO("Not yet implemented")
    }

    override suspend fun save(sepatu: Sepatu): String {
        TODO("Not yet implemented")
    }

    override suspend fun update(sepatu: Sepatu) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(sepatuId: String) {
        TODO("Not yet implemented")
    }

    override fun getSepatuById(sepatuId: String): Flow<Sepatu> {
        TODO("Not yet implemented")
    }

}


