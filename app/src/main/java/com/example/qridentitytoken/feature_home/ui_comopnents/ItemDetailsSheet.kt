package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillItemDetailsSheet(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {

    val sheetState = rememberModalBottomSheetState()

    //TODO remove dismiss on clicking outside sheet
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { homeScreenViewModel.closeBottomSheet() },
    ) {
        ItemDetailsSheetContent(homeScreenViewModel, navController)

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewFillItemDetailSheet() {
    QRIdentityTokenTheme {
        FillItemDetailsSheet(
            HomeScreenViewModel(),
            rememberNavController()
        )
    }
}