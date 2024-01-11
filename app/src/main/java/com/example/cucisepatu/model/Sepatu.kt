package com.example.cucisepatu.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
            entity = Jenis_Sepatu::class,
            parentColumns = ["id"],
            childColumns = ["jenisSepatu"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Sepatu(
    @PrimaryKey
    val id: String,
    val nama: String,
    val nohp: String,
    val alamat : String,
    val jenisSepatu: String,
    val tipeCuci: String
) {
    // Konstruktor tambahan
    constructor(): this("","","","","","")
}
