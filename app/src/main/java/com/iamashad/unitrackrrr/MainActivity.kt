package com.iamashad.unitrackrrr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.iamashad.unitrackrrr.navigation.AppNavigation
import com.iamashad.unitrackrrr.ui.theme.UniTrackrrrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniTrackrrrTheme {
                AppNavigation()
            }
        }
    }
}