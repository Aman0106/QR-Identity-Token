package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillItemDetailsSheet(
    homeScreenViewModel: HomeScreenViewModel,
    scaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavController
) {

    val sheetState = rememberModalBottomSheetState()

//    val scaffoldState = rememberBottomSheetScaffoldState()

    //TODO remove dismiss on clicking outside sheet
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { homeScreenViewModel.closeBottomSheet() },
    ) {
        ItemDetailsSheetContent(homeScreenViewModel, scaffoldState, coroutineScope, navController)

    }

//    BottomSheetScaffold(
//        scaffoldState = scaffoldState,
//        sheetContent = {
//            ItemDetailsSheetContent(homeScreenViewModel, scaffoldState, coroutineScope)
//        },
//        sheetPeekHeight = 0.dp,
//    ) {
//
//    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewFillItemDetailSheet() {
    FillItemDetailsSheet(
        HomeScreenViewModel(),
        rememberBottomSheetScaffoldState(),
        rememberCoroutineScope(),
        rememberNavController()
    )
}