package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.data.UserItemsRepository
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun  UserItems(
    userItemList: List<UserItem>,
    navHostController: NavHostController,
) {
    if (userItemList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Nothing to show yet :(",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        }
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(userItemList) {userItem ->
            UserItemCard(
                userItem = userItem,
                navHostController = navHostController,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUserItems() {
    QRIdentityTokenTheme {
        UserItems(
            UserItemsRepository.dummyList,
            rememberNavController(),
        )
    }
}

