package com.example.cucisepatu.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cucisepatu.R
import com.example.cucisepatu.ui.DetailCuci
import com.example.cucisepatu.ui.UIStateCuci
import com.example.cucisepatu.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val jenisSepatuItems by viewModel.jenisSepatuItems.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        EntryCuciBody(
            uiStateCuci = viewModel.uiStateKontak,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveKontak()
                    navigateBack()
                }
            },
            jenisSepatuItems = jenisSepatuItems,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}
@Composable
fun EntryCuciBody(
    uiStateCuci: UIStateCuci,
    onSiswaValueChange: (DetailCuci) -> Unit,
    onSaveClick: () -> Unit,
    jenisSepatuItems: List<String>,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues()
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        FormInputCuci(
            detailCuci = uiStateCuci.detailCuci,
            jenisSepatuItems = jenisSepatuItems,
            onValueChange = onSiswaValueChange,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        )

        // Other UI components...

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(text = stringResource(id = R.string.btn_submit))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputCuci(
    detailCuci: DetailCuci,
    modifier: Modifier,
    onValueChange : (DetailCuci) -> Unit = {},
    enabled: Boolean = true,
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailCuci.nama,
            onValueChange ={onValueChange(detailCuci.copy(nama = it))},
            label = { Text(stringResource(id = R.string.nama)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailCuci.alamat,
            onValueChange ={onValueChange(detailCuci.copy(alamat = it))},
            label = { Text(stringResource(R.string.alamat)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailCuci.nohp,
            onValueChange ={onValueChange(detailCuci.copy(nohp = it))},
            label = { Text(stringResource(R.string.telpon)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        BasicTextField(
            value = detailCuci.jenisSepatu,
            onValueChange = { onValueChange(detailCuci.copy(jenisSepatu = it)) },
            textStyle = MaterialTheme.typography.body1,
            label = { Text(stringResource(R.string.jenis_sepatu)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )

        // Dropdown Menu for jenisSepatu
        DropdownMenu(
            expanded = false,
            onDismissRequest = { /* handle dismiss if needed */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisSepatuItems.forEach { jenisSepatuItem ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(detailCuci.copy(jenisSepatu = jenisSepatuItem))
                    }
                ) {
                    Text(text = jenisSepatuItem)
                }
            }
        }

        // Other text fields...

        if (enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
        )
        OutlinedTextField(
            value = detailCuci.tipeCuci,
            onValueChange ={onValueChange(detailCuci.copy(tipeCuci = it))},
            label = { Text(stringResource(R.string.telpon)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        if (enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))

        )
    }
}