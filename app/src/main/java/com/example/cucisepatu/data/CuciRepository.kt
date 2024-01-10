package com.example.cucisepatu.data

import android.content.ContentValues
import android.util.Log
import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.model.Sepatu
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface CuciRepository {
    fun getAll(): Flow<List<Sepatu>>
    suspend fun save(sepatu: Sepatu): String
    suspend fun update(sepatu: Sepatu)
    suspend fun delete(sepatuId: String)
    fun getSepatuById(sepatuId: String): Flow<Sepatu>
    suspend fun getJenisSepatuItems(): List<Jenis_Sepatu>
    companion object
}

class CuciRepositoryImpl(private val firestore: FirebaseFirestore) : CuciRepository {
    override fun getAll(): Flow<List<Sepatu>> = flow {
        val snapshot = firestore.collection("Sepatu")
            .orderBy("Nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val sepatu = snapshot.toObjects(Sepatu::class.java)
        emit(sepatu)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(sepatu: Sepatu): String {
        return try {
            val documentReference = firestore.collection("Sepatu")
                .add(sepatu)
                .await()

            firestore.collection("Sepatu")
                .document(documentReference.id)
                .set(sepatu.copy(id = documentReference.id.toInt()))
            "Berhasil " + documentReference.id
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error Adding Document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(sepatu: Sepatu) {
        firestore.collection("Sepatu")
            .document(sepatu.id.toString())
            .set(sepatu)
            .await()
    }

    override suspend fun delete(sepatuId: String) {
        firestore.collection("Sepatu")
            .document(sepatuId)
            .delete()
            .await()
    }

    override fun getSepatuById(sepatuId: String): Flow<Sepatu> {
        return flow {
            val snapshot = firestore.collection("Sepatu")
                .document(sepatuId)
                .get()
                .await()
            val sepatu = snapshot.toObject(Sepatu::class.java)
            emit(sepatu!!)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getJenisSepatuItems(): List<Jenis_Sepatu> {
        return try {
            val snapshot = firestore.collection("JenisSepatu")
                .orderBy("nama", Query.Direction.ASCENDING)
                .get()
                .await()

            snapshot.toObjects(Jenis_Sepatu::class.java)
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error fetching JenisSepatu", e)
            emptyList()  // Return an empty list or handle the error as needed
        }
    }
}
