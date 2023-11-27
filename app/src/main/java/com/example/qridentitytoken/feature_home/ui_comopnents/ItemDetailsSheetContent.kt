package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.viewmodels.HomeScreenViewModel
import com.example.qridentitytoken.navgraphs.Destinations
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsSheetContent(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(MaterialTheme.spacing.extraSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val usrName = textFieldInput(label = "Name")
        val usrDesc = textFieldInput(label = "Description")
        val usrContact = textFieldInput(label = "Contact")
        val usrSecCon = textFieldInput(label = "Secondary Contact")
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface
            ),
            onClick = {
                //TODO add a check for invalid values
                homeScreenViewModel.submitItemDetails(
                    UserItem(itemName = usrName, contact = usrContact)
                )
                homeScreenViewModel.closeBottomSheet()
                navController.navigate(Destinations.qrScreen)

            },
        ) {
            Text(
                text = "Submit",
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }

    }
}

@Composable
fun textFieldInput(
    label: String = "",
): String {
    var txt by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = txt,
        onValueChange = {
            txt = it
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.inverseSurface,
            focusedBorderColor = MaterialTheme.colorScheme.inverseSurface,
            focusedLabelColor = MaterialTheme.colorScheme.inverseSurface
        ),
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    )

    return txt
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItemDetailsSheetContent() {
    QRIdentityTokenTheme {
        ItemDetailsSheetContent(
            HomeScreenViewModel(),
            rememberNavController()
        )
    }
}