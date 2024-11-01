package com.iamashad.unitrackrrr.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.components.AppTopBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                isHome = true,
                title = "UniTrackrrr"
            )
        },
        bottomBar = {

        }
    ) { contentPadding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
        }
    }
}
