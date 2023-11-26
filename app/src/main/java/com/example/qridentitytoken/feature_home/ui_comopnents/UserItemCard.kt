package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.navgraphs.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItemCard(
    userItem: UserItem,
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,
        ),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(16.dp)
            .aspectRatio(1f),
        onClick = {
            homeScreenViewModel.setCurrentUserItem(userItem)
            navHostController.navigate(Destinations.qrScreen)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = userItem.name,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewUserItemCard() {
    UserItemCard(
        userItem = UserItem("Pen", contact = "aman"),
        navHostController = rememberNavController(),
        homeScreenViewModel = HomeScreenViewModel()
        )
}