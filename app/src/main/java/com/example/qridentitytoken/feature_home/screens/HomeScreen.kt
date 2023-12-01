package com.example.qridentitytoken.feature_home.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.data.UserItemsRepository
import com.example.qridentitytoken.feature_home.ui_comopnents.FillItemDetailsSheet
import com.example.qridentitytoken.feature_home.ui_comopnents.ProfileInfo
import com.example.qridentitytoken.feature_home.ui_comopnents.UserItems
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun HomeScreen(
    userData: UserData?,
    onSignOut: () -> Unit = {},
    navController: NavHostController
) {

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
        ProfileInfo(userData = userData, onSignOut = onSignOut, navHostController = navController)
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
                )
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.large),
                    content = {
                        CustomFloatingActionButton(
                        )
                    }
                )

            }
            if(HomeScreenViewModel.isBottomSheetOpen())
                FillItemDetailsSheet(navController)
        }
    }
}

    @Composable
    fun CustomFloatingActionButton(
    ) {
        FloatingActionButton(
            onClick = {
                HomeScreenViewModel.openBottomSheet()
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
            userData = UserData("", "Cat Guy", "null", ""),
            navController = rememberNavController(),
        )

    }
}