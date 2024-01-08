package com.example.cucisepatu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tblSepatu", foreignKeys = [ForeignKey(entity = Jenis_Sepatu::class, parentColumns = ["id"], childColumns = ["jenis_sepatu"])])
data class Sepatu(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    @ColumnInfo(name = "nohp")
    val nohp: String,
    @ColumnInfo(name = "jenis_sepatu")
    val jenisSepatu: Int,
    @ColumnInfo(name = "tipe_cuci")
    val tipeCuci: String
) {
    // Konstruktor tambahan
    constructor(nama: String, nohp: String, jenisSepatu: Int, tipeCuci: String) : this(0, nama, nohp, jenisSepatu, tipeCuci)
}
