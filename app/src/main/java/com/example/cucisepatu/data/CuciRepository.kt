package com.example.cucisepatu.data

import android.content.ContentValues
import android.util.Log
import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.model.SepatuWithJenis
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface CuciRepository {
    fun getAll(): Flow<List<SepatuWithJenis>>
    suspend fun save(sepatu: SepatuWithJenis): String
    suspend fun update(sepatu: SepatuWithJenis)
    suspend fun delete(sepatuId: Int)
    fun getSepatuById(sepatuId: Int): Flow<SepatuWithJenis>
}

class CuciSepatuRepository(private val firestore: FirebaseFirestore) : CuciRepository {
    override fun getAll(): Flow<List<SepatuWithJenis>> = flow {
        val snapshot = firestore.collection("Sepatu")
            .orderBy("Nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val sepatuList = snapshot.toObjects(Sepatu::class.java)
            .map { sepatu ->
                val jenisSnapshot = firestore.collection("Jenis_Sepatu")
                    .document(sepatu.jenisSepatu.toString())
                    .get()
                    .await()
                val jenisSepatu = jenisSnapshot.toObject<Jenis_Sepatu>()
                SepatuWithJenis(sepatu, jenisSepatu!!)
            }
        emit(sepatuList)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(sepatuWithJenis: SepatuWithJenis): String {
        return try {
            val sepatu = sepatuWithJenis.sepatu
            val documentReference = firestore.collection("Sepatu")
                .add(sepatu)
                .await()

            firestore.collection("Sepatu")
                .document(documentReference.id)
                .set(sepatu.copy(id = documentReference.id.toInt())) // Convert to Int
                .await()

            val jenisSepatu = sepatuWithJenis.jenisSepatu
            firestore.collection("Jenis_Sepatu")
                .document(jenisSepatu.id.toString())
                .set(jenisSepatu)
                .await()

            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error Adding Document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(sepatuWithJenis: SepatuWithJenis) {
        val sepatu = sepatuWithJenis.sepatu
        val jenisSepatu = sepatuWithJenis.jenisSepatu

        firestore.collection("Sepatu")
            .document(sepatu.id.toString())
            .set(sepatu)
            .await()

        firestore.collection("Jenis_Sepatu")
            .document(jenisSepatu.id.toString())
            .set(jenisSepatu)
            .await()
    }

    override suspend fun delete(sepatuId: Int) {
        firestore.collection("Sepatu")
            .document(sepatuId.toString())
            .delete()
            .await()
    }

    override fun getSepatuById(sepatuId: Int): Flow<SepatuWithJenis> = flow {
        val sepatuSnapshot = firestore.collection("Sepatu")
            .document(sepatuId.toString())
            .get()
            .await()

        val jenisSnapshot = firestore.collection("Jenis_Sepatu")
            .document(sepatuId.toString())
            .get()
            .await()

        val sepatu = sepatuSnapshot.toObject<Sepatu>()
        val jenisSepatu = jenisSnapshot.toObject<Jenis_Sepatu>()

        if (sepatu != null && jenisSepatu != null) {
            emit(SepatuWithJenis(sepatu, jenisSepatu))
        }
    }.flowOn(Dispatchers.IO)
}
