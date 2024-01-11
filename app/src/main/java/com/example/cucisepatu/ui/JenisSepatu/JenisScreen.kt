package com.example.cucisepatu.ui.JenisSepatu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cucisepatu.model.Jenis_Sepatu
import com.example.cucisepatu.navigation.DestinasiNavigasi
import com.example.cucisepatu.ui.PenyediaViewModel
import com.example.cucisepatu.ui.PesanTopAppBar

object DestinasiJenis : DestinasiNavigasi {
    override val route = "jenis"
    override val titleRes = "Jenis Sepatu"
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JenisScreen (
    navigateToJenisItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Jenis_Sepatu) -> Unit = {},
    viewModel: JenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PesanTopAppBar(
                title = "Jenis Sepatu",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToJenisItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiJenisPesan by viewModel.jenisUIState.collectAsState()
        BodyJenisHome(
            itemJenis_Sepatu = uiJenisPesan.listJenis_Sepatu,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onPesanClick = onDetailClick
        )
    }
}

@Composable
fun BodyJenisHome(
    itemJenis_Sepatu: List<Jenis_Sepatu>,
    modifier: Modifier = Modifier,
    onPesanClick: (Jenis_Sepatu) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemJenis_Sepatu.isEmpty()) {
            Text(
                text = "Tidak ada data pesanan...",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPesanan(
                itemJenis_Sepatu = itemJenis_Sepatu,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onPesanClick(it) } // Correct the type of the parameter
            )
        }
    }
}

@Composable
fun ListPesanan(
    itemJenis_Sepatu: List<Jenis_Sepatu>,
    modifier: Modifier = Modifier,
    onItemClick: (Jenis_Sepatu) -> Unit // Correct the type of the parameter
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemJenis_Sepatu, key = { it.id }) { jenisSepatu ->
            DataPesanan(
                jenisSepatu = jenisSepatu,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(jenisSepatu) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataPesanan(
    jenisSepatu: Jenis_Sepatu,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = jenisSepatu.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}
