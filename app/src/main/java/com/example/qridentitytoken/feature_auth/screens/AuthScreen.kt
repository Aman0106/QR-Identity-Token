package com.example.qridentitytoken.feature_auth.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_auth.SignInState


@Composable
fun AuthScreen(
    navHostController: NavHostController,
    state: SignInState = SignInState(),
    onSignInCLick: () -> Unit = {}
) {


    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
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
            Text(text = "Sign In", fontSize = 72.sp)
            Button(onClick = onSignInCLick) {
                Text(text = "Sign In")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAuthScreen() {
    AuthScreen(rememberNavController(), onSignInCLick = {})
}