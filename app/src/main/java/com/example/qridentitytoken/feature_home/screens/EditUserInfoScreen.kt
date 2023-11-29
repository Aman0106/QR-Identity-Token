package com.example.qridentitytoken.feature_home.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.qridentitytoken.R
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.feature_auth.data.UserGeneratedData
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.ui_comopnents.textFieldInput
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.navgraphs.Destinations
import com.example.qridentitytoken.shared_videomodel.UserDataViewModel
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@Composable
fun EditUserInfoScreen(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        EditUserInfoAppBar(navHostController = navHostController)
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50)
                    )
            ) {
                UserImage(userData = UserDataViewModel.getSignedInUserData())
            }

            // Name
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = UserDataViewModel.getSignedInUserData()?.userName ?: "Mr. Fluff",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )


            SaveUserButton(
                name = textFieldInput("Name"),
                email = textFieldInput("Email"),
                contact = textFieldInput("Contact"),
                homeScreenViewModel
            )
        }
    }


}

@Composable
fun SaveUserButton(
    name: String,
    email: String,
    contact: String,
    homeScreenViewModel: HomeScreenViewModel
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.large,
                vertical = MaterialTheme.spacing.extraLarge
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        ),
        onClick = {

//            UserDataViewModel.setUserGeneratedData(
//                UserGeneratedData(
//                    name,
//                    email,
//                    contact,
//                    ""
//                )
//            )
//            homeScreenViewModel.updateUserDetails()


        },
    ) {
        Text(
            text = "Save",
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
    }
}

@Composable
fun UserImage(userData: UserData?) {
    if (userData?.profilePictureUrl != null) {
        AsyncImage(
            model = userData?.profilePictureUrl,
            contentDescription = "profile Picture",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(50)),
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.cat_dp),
            contentDescription = "profile Picture",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(50)),
        )
    }
}

@Composable
fun EditUserInfoAppBar(
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

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEditUserInfo() {
    QRIdentityTokenTheme {
        EditUserInfoScreen(rememberNavController(), HomeScreenViewModel())

    }
}
