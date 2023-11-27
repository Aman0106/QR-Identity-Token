package com.example.qridentitytoken.feature_qrpage.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun QRScreenAppBar(
    navHostController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                navHostController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Sign Out",
                modifier = Modifier
                    .size(MaterialTheme.spacing.extraLarge * 2.2f),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewQRScreenAppBar() {
    QRIdentityTokenTheme {
        QRScreenAppBar(navHostController = rememberNavController())
    }
}