package com.example.qridentitytoken.feature_home.ui_comopnents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.qridentitytoken.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsSheetContent(
    homeScreenViewModel: HomeScreenViewModel,
    scaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.extraSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val usrName = TextFieldInput(label = "Name")
        val usrDesc = TextFieldInput(label = "Description")
        val usrContact = TextFieldInput(label = "Contact")
        val usrSecCon = TextFieldInput(label = "Secondary Contact")
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large),
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
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }

    }
}

@Composable
fun TextFieldInput(
    label: String = "",
): String {
    var txt by remember {
        mutableStateOf("")
    }
    TextField(
        value = txt,
        onValueChange = {
            txt = it
        },
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    )

    return txt;
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewItemDetailsSheetContent() {
    ItemDetailsSheetContent(
        HomeScreenViewModel(),
        rememberBottomSheetScaffoldState(),
        rememberCoroutineScope(),
        rememberNavController()
    )
}