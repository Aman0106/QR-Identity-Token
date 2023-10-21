package com.example.qridentitytoken.feature_auth.screens

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
fun AuthScreen(
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
            Text(text = "Auth", fontSize = 72.sp)
            Button(onClick = {
                navHostController.popBackStack()
                navHostController.navigate(Graphs.homeGraph)
            }) {
                Text(text = "Home")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAuthScreen() {
    AuthScreen(rememberNavController())
}