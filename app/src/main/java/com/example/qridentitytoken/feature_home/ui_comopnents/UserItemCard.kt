package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.navgraphs.Destinations
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItemCard(
    userItem: UserItem,
    navHostController: NavHostController,
) {
    OutlinedCard(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .aspectRatio(1.2f),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(40.dp),
        onClick = {
            HomeScreenViewModel.setCurrentUserItem(userItem)
            navHostController.navigate(Destinations.qrScreen)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = userItem.itemName,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUserItemCard() {
   QRIdentityTokenTheme {
       UserItemCard(
           userItem = UserItem("Pen"),
           navHostController = rememberNavController(),
       )
   }
}