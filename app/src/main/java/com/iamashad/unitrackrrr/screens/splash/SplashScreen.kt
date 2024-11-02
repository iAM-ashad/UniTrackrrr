package com.iamashad.unitrackrrr.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.navigation.TrackrScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Surface(
        modifier=Modifier
            .fillMaxSize(),
        color = Color(224, 240, 255,255)
    )
    {
        Image(painterResource(id = R.drawable.splash_logo),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .padding(5.dp)
        )
    }
    LaunchedEffect(key1 = true) {
        delay(4000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(TrackrScreens.LOGINSCREEN.name)
        } else {
            navController.navigate(TrackrScreens.HOMESCREEN.name)
            }
        }
}