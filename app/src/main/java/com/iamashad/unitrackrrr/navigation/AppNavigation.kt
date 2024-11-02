package com.iamashad.unitrackrrr.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iamashad.unitrackrrr.screens.addtolost.AddToLostScreen
import com.iamashad.unitrackrrr.screens.fee.FeeScreen
import com.iamashad.unitrackrrr.screens.fee.ReceiptViewModel
import com.iamashad.unitrackrrr.screens.fee.ViewReceiptsScreen
import com.iamashad.unitrackrrr.screens.home.HomeScreen
import com.iamashad.unitrackrrr.screens.login.LoginScreen
import com.iamashad.unitrackrrr.screens.register.RegisterScreen
import com.iamashad.unitrackrrr.screens.splash.SplashScreen
import com.iamashad.unitrackrrr.screens.viewfound.ViewFoundScreen
import com.iamashad.unitrackrrr.screens.viewfound.ViewFoundViewModel
import com.iamashad.unitrackrrr.screens.viewlost.ViewLostScreen
import com.iamashad.unitrackrrr.screens.viewlost.ViewLostViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TrackrScreens.SPLASHSCREEN.name) {
        composable(TrackrScreens.SPLASHSCREEN.name) {
            SplashScreen(navController = navController)
        }
        composable(TrackrScreens.REGISTERSCREEN.name) {
            RegisterScreen(navController = navController)
        }
        composable(TrackrScreens.HOMESCREEN.name) {
            HomeScreen(navController = navController)
        }
        composable(TrackrScreens.ADDTOLOSTSCREEN.name) {
            AddToLostScreen(navController = navController)
        }
        composable(TrackrScreens.FEESCREEN.name) {
            FeeScreen(navController = navController)
        }
        composable(TrackrScreens.LOGINSCREEN.name) {
            LoginScreen(navController = navController)
        }
        composable(TrackrScreens.VIEWFOUNDSCREEN.name) {
            val viewModel = hiltViewModel<ViewFoundViewModel>()
            ViewFoundScreen(navController = navController, viewModel)
        }
        composable(TrackrScreens.VIEWLOSTSCREEN.name) {
            val viewModel = hiltViewModel<ViewLostViewModel>()
            ViewLostScreen(navController = navController,viewModel)
        }
        composable(
            route = "${TrackrScreens.VIEWRECEIPTSSCREEN.name}/{semester}",
            arguments = listOf(
                navArgument(name = "semester") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val semester = backStackEntry.arguments?.getInt("semester") ?: 1
            val viewModel = hiltViewModel<ReceiptViewModel>()
            ViewReceiptsScreen(navController = navController, semester = semester, viewModel)
        }
    }
}