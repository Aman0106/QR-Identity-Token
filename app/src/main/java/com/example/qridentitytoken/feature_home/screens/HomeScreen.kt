package com.example.qridentitytoken.feature_home.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.qridentitytoken.R
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.data.UserItemsRepository
import com.example.qridentitytoken.feature_home.ui_comopnents.FillItemDetailsSheet
import com.example.qridentitytoken.feature_home.ui_comopnents.ProfileInfo
import com.example.qridentitytoken.feature_home.ui_comopnents.UserItems
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.LocalSpacing
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.Spacing
import com.example.qridentitytoken.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    userData: UserData?,
    onSignOut: () -> Unit = {},
    navController: NavHostController
) {

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val userItemsState = remember { mutableStateOf(emptyList<UserItem>()) }

    //TODO learn about coroutines to optimize the code
    LaunchedEffect(key1 = Unit) {
        val fetchedUserItems = UserItemsRepository.fetchUserItems()
        userItemsState.value = fetchedUserItems
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ProfileInfo(userData = userData, onSignOut = onSignOut)
//        Divider(
//            color = MaterialTheme.colorScheme.inverseSurface,
//            thickness = 1.dp,
//            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
//        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                UserItems(
                    userItemList = userItemsState.value,
                    navHostController = navController,
                    homeScreenViewModel = homeScreenViewModel
                )
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.large),
                    content = {
                        CustomFloatingActionButton(
                            homeScreenViewModel,
                            scaffoldState,
                            coroutineScope
                        )
                    }
                )

            }
            if(homeScreenViewModel.isBottomSheetOpen())
                FillItemDetailsSheet(homeScreenViewModel, scaffoldState, coroutineScope, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomFloatingActionButton(
        homeScreenViewModel: HomeScreenViewModel,
        scaffoldState: BottomSheetScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        FloatingActionButton(
            onClick = {
                homeScreenViewModel.openBottomSheet()
            },
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.background
        ) {
            Icon(
                Icons.Filled.Add,
                "Floating action button.",
                Modifier.size(30.dp)
                )
        }
    }


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    QRIdentityTokenTheme {
        HomeScreen(
            HomeScreenViewModel(),
            userData = UserData("", "Cat Guy", null),
            navController = rememberNavController(),
        )

    }
}