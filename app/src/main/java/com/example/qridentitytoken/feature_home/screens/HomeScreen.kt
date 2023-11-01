package com.example.qridentitytoken.feature_home.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.qridentitytoken.R
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.feature_home.ui_comopnents.FillItemDetailsSheet
import com.example.qridentitytoken.feature_home.ui_comopnents.ProfileInfo
import com.example.qridentitytoken.feature_home.ui_comopnents.UserItems
import com.example.qridentitytoken.feature_home.ui_comopnents.dummyList
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.ui.theme.LocalSpacing
import com.example.qridentitytoken.ui.theme.Spacing
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userData: UserData?,
    onSignOut: () -> Unit = {},
) {
    val homeScreenViewModel = remember {
        HomeScreenViewModel()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        ProfileInfo(userData = userData, onSignOut = onSignOut)
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                UserItems(userItemList = dummyList)
                Box(
                    contentAlignment =Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.large),
                    content = { CustomFloatingActionButton(homeScreenViewModel) }
                )

            }
            if (homeScreenViewModel.isBottomSheetOpen())
                FillItemDetailsSheet(homeScreenViewModel)
        }
    }

}
@Composable
fun CustomFloatingActionButton(
    homeScreenViewModel: HomeScreenViewModel,
) {
    FloatingActionButton(
        onClick = {
            homeScreenViewModel.openBottomSheet()
        },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navHostController = rememberNavController(), userData = UserData("", "Cat Guy", null))
}