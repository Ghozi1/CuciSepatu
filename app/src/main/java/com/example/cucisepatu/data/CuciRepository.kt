package com.example.cucisepatu.data

import com.example.cucisepatu.model.Sepatu
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface CuciRepository{
    fun getAll() : Flow<List<Sepatu>>
    suspend fun save(sepatu: Sepatu) : String
    suspend fun update (sepatu: Sepatu)
    suspend fun delete (sepatuId: String)
    fun getSepatuById(sepatuId: String) : Flow<Sepatu>
}

class CuciSepatuRepository(private val firestore: FirebaseFirestore) : CuciRepository{
    override fun getAll(): Flow<List<Sepatu>> = flow {
        val snapshot = firestore.collection("Sepatu")
            .orderBy("Nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val kontak = snapshot.toObjects(Sepatu::class.java)
        emit(kontak)
    } .flowOn(Dispatchers.IO)

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


