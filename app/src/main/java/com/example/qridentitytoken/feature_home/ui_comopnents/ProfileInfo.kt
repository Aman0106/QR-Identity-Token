package com.example.qridentitytoken.feature_home.ui_comopnents

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.qridentitytoken.R
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.navgraphs.Destinations
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun ProfileInfo(
    userData: UserData?,
    onSignOut: () -> Unit,
    navHostController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = onSignOut
            ) {
                Icon(
                    imageVector = Icons.Outlined.ExitToApp,
                    contentDescription = "Sign Out",
                    modifier = Modifier
                        .size(MaterialTheme.spacing.extraLarge * 2.2f),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "Items",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )

            Box(
                modifier = Modifier
                    .clickable {
                        navHostController.navigate(Destinations.editUserInfoScreen)
                    }
            ) {
                UserImage(userData = userData)
            }

        }
        Card(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
//            border = BorderStroke(0.dp, MaterialTheme.colorScheme.primary),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(20.dp)

        ) {
            Text(
                text = "Welcome, ${userData?.userName ?: "Mr. Fluff"}",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp),
                textAlign = TextAlign.Start,
            )
        }

    }

}

@Composable
fun UserImage(userData: UserData?) {
    if (userData?.profilePictureUrl != null) {
        AsyncImage(
            model = userData?.profilePictureUrl,
            contentDescription = "profile Picture",
            modifier = Modifier
                .size(MaterialTheme.spacing.extraLarge * 2.2f)
                .padding(MaterialTheme.spacing.medium)
                .clip(RoundedCornerShape(20))
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.cat_dp),
            contentDescription = "profile Picture",
            modifier = Modifier
                .size(MaterialTheme.spacing.extraLarge * 2.2f)
                .padding(MaterialTheme.spacing.medium)
                .clip(RoundedCornerShape(20))
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserInfo() {
    QRIdentityTokenTheme {
        ProfileInfo(
            userData = UserData("dhwkjd", "Cat","" ,"null"),
            onSignOut = {},
            navHostController = rememberNavController()
        )
    }
}
