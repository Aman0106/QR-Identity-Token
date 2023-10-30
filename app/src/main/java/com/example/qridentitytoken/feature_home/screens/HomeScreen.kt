package com.example.qridentitytoken.feature_home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.qridentitytoken.navgraphs.Graphs

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    userData: UserData?,
    onSignOut: () -> Unit = {}
) {
    Column () {
        Column {
            Spacer(modifier = Modifier.height(90.dp))
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                if(userData?.profilePictureUrl != null) {
                    AsyncImage(
                        model = userData?.profilePictureUrl,
                        contentDescription = "profile Picture",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = userData?.username?: "Dummy", fontSize = 72.sp)
                Button(onClick = onSignOut) {
                    Text(text = "Sign Out")
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController(), UserData("", "ABC" , null))
}