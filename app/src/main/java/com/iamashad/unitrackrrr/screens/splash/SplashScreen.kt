package com.iamashad.unitrackrrr.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.iamashad.unitrackrrr.navigation.TrackrScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Splash Screen"
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(TrackrScreens.LOGINSCREEN.name)
        } else {
            navController.navigate(TrackrScreens.VIEWFOUNDSCREEN.name)
        }
    }
}