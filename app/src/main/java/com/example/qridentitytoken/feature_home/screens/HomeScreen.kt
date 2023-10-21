package com.example.qridentitytoken.feature_home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.navgraphs.Graphs

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
       Column (
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(text = "Home", fontSize = 72.sp)
           Button(onClick = {
                navHostController.navigate(Graphs.authGraph
                )
           }) {
                Text(text = "Auth")
           }
       }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}