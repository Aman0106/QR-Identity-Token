package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.qridentitytoken.feature_home.data.UserItem

@Composable
fun UserItems(
    userItemList: List<UserItem>
) {
    if (userItemList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                text = "Nothing to show yet :(",
                fontSize = 72.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(userItemList) {userItem ->
            UserItemCard(userItem = userItem)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUserItems() {
    UserItems(dummyList)
}

val dummyList = listOf<UserItem>(
    UserItem("Pen"),
    UserItem("Pen"),
    UserItem("Pen"),
    UserItem("Pen"),
    UserItem("Pen")
)