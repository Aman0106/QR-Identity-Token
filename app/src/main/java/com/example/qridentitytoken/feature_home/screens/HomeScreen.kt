package com.example.qridentitytoken.feature_home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.R
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.navgraphs.Graphs

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    userData: UserData = UserData("", "" , ""),
    onSignOut: () -> Unit = {}
) {
    Column () {
        Column {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            ){
                Image(
                    painter = painterResource(id = R.drawable.cat_dp),
                    contentDescription = "DP"
                )
            }
        }
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

}


@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}