package com.example.cucisepatu.ui.screen

import androidx.compose.ui.Modifier
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.navigation.DestinasiNavigasi

object DestinasiHasil : DestinasiNavigasi {
    override val route = "hasil"
    override val titleRes = "Sepatu"
}

fun PesanCard(
    sepatu: Sepatu,
    onDeleteClick: (Sepatu) -> Unit = {},
    modifier: Modifier = Modifier
){

}