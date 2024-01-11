package com.example.cucisepatu.ui.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cucisepatu.navigation.DestinasiNavigasi
import com.example.cucisepatu.ui.PenyediaViewModel
import com.example.cucisepatu.ui.PesanTopAppBar
import com.example.cucisepatu.ui.pemesanan.EntryBody
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Pesanan"
    const val sepatuId = "itemId"
    val routeWithArgs = "${EditDestination.route}/{$sepatuId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            PesanTopAppBar(
                title =EditDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            pemesananUIState = viewModel.sepatuUiState,
            onPesanValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateSepatu()
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