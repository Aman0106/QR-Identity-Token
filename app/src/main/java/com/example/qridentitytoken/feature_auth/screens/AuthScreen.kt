package com.example.qridentitytoken.feature_auth.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_auth.SignInState
import com.example.qridentitytoken.feature_qrpage.QRCode
import com.example.qridentitytoken.feature_qrpage.QRUtils
import com.example.qridentitytoken.feature_qrpage.QrSizes
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing


@Composable
fun AuthScreen(
    state: SignInState = SignInState(),
    onSignInCLick: () -> Unit = {}
) {

    val bitmap = remember {
        QRUtils.generateQRCode(
            "QRIdentity Token"
        )
    }

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface
            ),
            shape = RoundedCornerShape(20.dp)

        ) {
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        QRCode(size = QrSizes().large, bitmap = bitmap)
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onSignInCLick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            ),
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAuthScreen() {
    QRIdentityTokenTheme {
        AuthScreen(onSignInCLick = {})
    }
}