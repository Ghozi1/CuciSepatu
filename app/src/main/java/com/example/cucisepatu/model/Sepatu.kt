package com.example.cucisepatu.model

data class Sepatu (
    val id : String,
    val nama : String,
    val nohp : String,
    val tipe_sepatu : String,
    val tipe_cuci : String
){
    constructor() : this ("","","","","")
}