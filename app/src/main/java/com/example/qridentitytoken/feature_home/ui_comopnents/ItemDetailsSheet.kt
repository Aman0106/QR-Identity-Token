package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillItemDetailsSheet(
    homeScreenViewModel: HomeScreenViewModel
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { homeScreenViewModel.closeBottomSheet() },
    ) {
        TextFieldInput(label = "Name")

    }
}

@Composable
fun TextFieldInput(
    label: String = "",
): String {
    var txt by remember {
        mutableStateOf("")
    }
    TextField(
        value = txt,
        onValueChange = {
            txt = it
        },
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    )

    return txt;
}


@Preview
@Composable
fun PreviewFillItemDetailSheet() {
    FillItemDetailsSheet(HomeScreenViewModel())
}