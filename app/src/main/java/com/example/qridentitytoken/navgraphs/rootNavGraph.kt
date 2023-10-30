package com.example.qridentitytoken.navgraphs

import android.companion.CompanionDeviceManager.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qridentitytoken.feature_auth.GoogleAuthUiClient
import com.example.qridentitytoken.feature_auth.screens.AuthScreen
import com.example.qridentitytoken.feature_auth.viewmodel.SignInViewModel
import com.example.qridentitytoken.feature_home.screens.HomeScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun rootNavGraph(
    navHostController: NavHostController,
    context: Context,
    appLifeCycleScope: LifecycleCoroutineScope
) {
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    NavHost(
        navController = navHostController,
        startDestination = Graphs.authGraph) {
        navigation(
            startDestination = Destinations.authScreen,
            route = Graphs.authGraph
        ){
            composable(Destinations.authScreen) {
                val viewModel = viewModel<SignInViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                
                LaunchedEffect(key1 = Unit) {// Unit makes it recompose only once when it is first created
                    if(googleAuthUiClient.getSignedInUser() != null)
                        navHostController.navigate(Graphs.homeGraph)
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if(result.resultCode == RESULT_OK) {
                            appLifeCycleScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )

                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )
                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            context,
                            "Success",
                            Toast.LENGTH_LONG
                        ).show()

                        navHostController.navigate(Graphs.homeGraph)
                        viewModel.resetState()
                    }
                }

                AuthScreen(
                    navHostController = navHostController,
                    state = state,
                    onSignInCLick = {
                        appLifeCycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )

                        }
                    }
                )
            }
        }
        navigation(
            startDestination = Destinations.homeScreen,
            route = Graphs.homeGraph
        ) {
            composable(Destinations.homeScreen) {
                HomeScreen(
                    navHostController = navHostController,
                    userData = googleAuthUiClient.getSignedInUser(),
                    onSignOut = {
                        appLifeCycleScope.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                context,
                                "SignOut Success",
                                Toast.LENGTH_LONG
                            ).show()
                            navHostController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}
