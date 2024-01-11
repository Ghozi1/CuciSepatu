package com.example.cucisepatu.ui.jenissepatu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.cucisepatu.ui.PemesananEvent
import com.example.cucisepatu.ui.PenyediaViewModel
import com.example.cucisepatu.ui.PesanTopAppBar
import com.example.cucisepatu.ui.pemesanan.PemesananViewModel
import kotlinx.coroutines.launch


object DestinasiJenis : DestinasiNavigasi {
    override val route = "item_jenis"
    override val titleRes = "Entry Jenis"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisSepatuScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    pemesananViewModel: PemesananViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PesanTopAppBar(
                title = DestinasiJenis.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        EntryBody(
            pemesananUIState = pemesananViewModel.pemesananUIState,
            onPesanValueChange = pemesananViewModel::updatePesananUIState,
            onSaveClick = {
                coroutineScope.launch {
                    pemesananViewModel.addPesanan()
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
    pemesananUIState: PemesananJenis,
    onPesanValueChange: (PemesananEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputJenis(
            pemesananEvent = pemesananUIState.pemesananEvent,
            onValueChange = onPesanValueChange,
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
fun FormInputJenis(
    pemesananEvent: PemesananEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PemesananEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = pemesananEvent.nama,
            onValueChange = { onValueChange(pemesananEvent.copy(nama = it)) },
            label = { Text("nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}