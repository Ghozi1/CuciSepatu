package com.example.cucisepatu.data

import com.example.cucisepatu.model.Sepatu
import kotlinx.coroutines.flow.Flow

interface CuciRepository{
    fun getAll() : Flow<List<Sepatu>>
    suspend fun save(sepatu: Sepatu) : String
    suspend fun update (sepatu: Sepatu)
    suspend fun delete (sepatuId: String)
    fun getSepatuById(sepatuId: String) : Flow<Sepatu>
}