package com.iamashad.unitrackrrr.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.components.AppTopBar
import com.iamashad.unitrackrrr.navigation.TrackrScreens

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar =
        {
            AppTopBar(
                navController = navController,
                isHome = true,
                title = "UniTrackrr"
            )
        },
    ) {contentPadding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
        {
            Image(painter = painterResource(R.drawable.homescreen_background),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.
                fillMaxSize())
            Column(
                modifier=Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Card(
                    modifier=Modifier
                        .padding(20.dp)
                        .weight(1f)
                        .clickable{
                            navController.navigate(TrackrScreens.FEESCREEN.name)
                        },
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(240, 240, 240)
                    ),
                    shape= RoundedCornerShape(100.dp)
                )
                {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        Image(
                            painterResource(id = R.drawable.need_receipt),
                            contentDescription = "Logo",

                            contentScale = ContentScale.Fit
                        )
                    }
                }
                Card(
                    modifier=Modifier
                        .padding(20.dp)
                        .weight(1f)
                        .clickable{
                            navController.navigate(TrackrScreens.VIEWLOSTSCREEN.name)
                        },
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(240, 240, 240)
                    ),
                    shape= RoundedCornerShape(100.dp)
                )
                {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        Image(
                            painterResource(id = R.drawable.lost_something),
                            contentDescription = "Logo",

                            contentScale = ContentScale.Fit
                        )
                    }
                }
                Card(
                    modifier=Modifier
                        .padding(20.dp)
                        .weight(1f)
                        .clickable{
                            navController.navigate(TrackrScreens.VIEWFOUNDSCREEN.name)
                        },
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(240, 240, 240)
                    ),
                    shape= RoundedCornerShape(100.dp)
                )
                {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        Image(
                            painterResource(id = R.drawable.found_something),
                            contentDescription = "Logo",

                            contentScale = ContentScale.Fit
                        )
                    }
                }

            }
        }
    }
}
