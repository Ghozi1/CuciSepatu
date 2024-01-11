package com.example.cucisepatu.data

import android.content.ContentValues
import android.util.Log
import com.example.cucisepatu.model.Jenis_Sepatu
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface JenisSepatuRepository {
    fun getAll(): Flow<List<Jenis_Sepatu>>
    suspend fun save(jenisSepatu: Jenis_Sepatu): String
}

class JenisSepatuRepositoryImpl(private val firestore: FirebaseFirestore) : CuciRepository {
    override fun getAll(): Flow<List<Jenis_Sepatu>> = flow {
        val snapshot = firestore.collection("Sepatu")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val sepatu = snapshot.toObjects(Jenis_Sepatu::class.java)
        emit(sepatu)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(jenisSepatu: Jenis_Sepatu): String {
        return try {
            val documentReference = firestore.collection("Sepatu")
                .add(jenisSepatu)
                .await()

            firestore.collection("Sepatu")
                .document(documentReference.id)
                .set(jenisSepatu.copy(id = documentReference.id))
            "Berhasil " + documentReference.id
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error Adding Document", e)
            "Gagal $e"
        }
    }
}
