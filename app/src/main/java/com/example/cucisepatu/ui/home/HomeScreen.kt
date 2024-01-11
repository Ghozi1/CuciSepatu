package com.example.cucisepatu.ui.home

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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
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
import com.example.cucisepatu.model.Sepatu
import com.example.cucisepatu.navigation.DestinasiNavigasi
import com.example.cucisepatu.ui.PenyediaViewModel
import com.example.cucisepatu.ui.PesanTopAppBar


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Pesanan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PesanTopAppBar(
                title = "Pesanan",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
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
        val uiStatePesan by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemSepatu = uiStatePesan.listPesan,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onPesanClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemSepatu: List<Sepatu>,
    modifier: Modifier = Modifier,
    onPesanClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemSepatu.isEmpty()) {
            Text(
                text = "Tidak ada data pesanan...",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPesanan(
                itemSepatu = itemSepatu,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onPesanClick(it.id) }
            )
        }
    }
}

@Composable
fun ListPesanan(
    itemSepatu: List<Sepatu>,
    modifier: Modifier = Modifier,
    onItemClick: (Sepatu) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemSepatu, key = { it.id }) { sepatu ->
            DataPesanan(
                sepatu = sepatu,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(sepatu) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataPesanan(
    sepatu: Sepatu,
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
                    text = sepatu.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = sepatu.nohp,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = sepatu.alamat,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = sepatu.jenisSepatu,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = sepatu.tipeCuci,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}