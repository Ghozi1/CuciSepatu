package com.example.cucisepatu.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "tblJenisSepatu")
data class Jenis_Sepatu(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val nama: String
)

data class SepatuWithJenis(
    @Embedded
    val sepatu: Sepatu,
    @Relation(
        parentColumn = "jenis_sepatu",
        entityColumn = "id"
    )
    val jenisSepatu: Jenis_Sepatu
)

