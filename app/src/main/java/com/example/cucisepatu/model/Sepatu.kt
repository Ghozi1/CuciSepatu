package com.example.cucisepatu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tblSepatu", foreignKeys = [ForeignKey(entity = Jenis_Sepatu::class, parentColumns = ["id"], childColumns = ["jenis_sepatu"])])
data class Sepatu(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val nama: String,
    @ColumnInfo(name = "nohp")
    val nohp: String,
    val alamat : String,
    @ColumnInfo(name = "jenis_sepatu")
    val jenisSepatu: String,
    @ColumnInfo(name = "tipe_cuci")
    val tipeCuci: String
) {
    // Konstruktor tambahan
    constructor(): this("","","","","","")
}
