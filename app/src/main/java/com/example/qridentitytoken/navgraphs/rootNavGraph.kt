package com.example.qridentitytoken.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qridentitytoken.feature_auth.screens.AuthScreen
import com.example.qridentitytoken.feature_home.screens.HomeScreen

@Composable
fun rootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Graphs.authGraph) {
        navigation(
            startDestination = Destinations.authScreen,
            route = Graphs.authGraph
        ){
            composable(Destinations.authScreen) {
                AuthScreen(navHostController = navHostController)
            }
        }
        navigation(
            startDestination = Destinations.homeScreen,
            route = Graphs.homeGraph
        ) {
            composable(Destinations.homeScreen) {
                HomeScreen(navHostController = navHostController)
            }
        }
    }
}
