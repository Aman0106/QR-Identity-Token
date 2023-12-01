package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillItemDetailsSheet(
    navController: NavController
) {

    val sheetState = rememberModalBottomSheetState()

    //TODO remove dismiss on clicking outside sheet
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { HomeScreenViewModel.closeBottomSheet() },
    ) {
        ItemDetailsSheetContent(navController)

    }

}

@Preview
@Composable
fun PreviewFillItemDetailSheet() {
    QRIdentityTokenTheme {
        FillItemDetailsSheet(
            rememberNavController()
        )
    }
}