package com.example.cucisepatu.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.navigation.DestinasiNavigasi

object DestinasiHasil : DestinasiNavigasi {
    override val route = "hasil"
    override val titleRes = "Sepatu"
}

@Composable
fun PesanCard(
    sepatu: Sepatu,
    onDeleteClick: (Sepatu) -> Unit = {},
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        }
    }
}
