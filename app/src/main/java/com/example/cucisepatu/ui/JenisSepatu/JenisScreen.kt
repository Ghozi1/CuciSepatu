package com.example.cucisepatu.ui.JenisSepatu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cucisepatu.navigation.DestinasiNavigasi
import com.example.cucisepatu.ui.PenyediaViewModel
import com.example.cucisepatu.ui.PesanTopAppBar
import com.example.cucisepatu.ui.pemesanan.DestinasiEntry
import kotlinx.coroutines.launch

object DestinasiJenis : DestinasiNavigasi {
    override val route = "jenis"
    override val titleRes = "Jenis Sepatu"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    jenisViewModel: JenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PesanTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        EntryBody(
            jenisUIState = jenisViewModel.jenisUIState,
            onJenisValueChange = jenisViewModel::updateJenisUIState,
            onSaveClick = {
                coroutineScope.launch {
                    jenisViewModel.addJenis()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    jenisUIState: JenisUIState,
    onJenisValueChange: (JenisEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        JenisInput(
            pemesananJenisEvent = jenisUIState.jenisEvent,
            onValueChange = onJenisValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisInput(
    pemesananJenisEvent: JenisEvent,
    modifier: Modifier = Modifier,
    onValueChange: (JenisEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = pemesananJenisEvent.nama,
            onValueChange = { onValueChange(pemesananJenisEvent.copy(nama = it)) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}