package com.iamashad.unitrackrrr.screens.viewfound

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.components.AppTopBar
import com.iamashad.unitrackrrr.model.FoundItem
import com.iamashad.unitrackrrr.utils.LoadImageWithGlide

@Composable
fun ViewFoundScreen(
    navController: NavController,
    viewModel: ViewFoundViewModel
) {
    val listOfItems by viewModel.foundItems.collectAsState()

    // Log the items (if necessary)
    if (listOfItems.isNotEmpty()) {
        Log.d("VIEW LOST SCREEN", listOfItems.toString())
    }

    // Pass the observed list to the LostList composable
    ViewLostScreenContent(
        lostItems = listOfItems,
        navController = navController
    )
}
@Composable
fun ViewLostScreenContent(
    lostItems: List<FoundItem>,
    navController: NavController
) {
    Column(
        modifier = Modifier
    ) {
        AppTopBar(navController = navController, isHome = false, "")
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Items Found Lost On Campus Premises",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
        )
        HorizontalDivider(thickness = 1.dp)
        FoundList(lostItems,navController)
    }
}
@Composable
fun FoundList(
    foundItems: List<FoundItem>,
    navController: NavController
) {
    LazyColumn {
        items(foundItems) { item->
            FoundItem(
                foundItem = item,
                navController = navController
            )
        }
    }
}

@Composable
fun FoundItem(
    foundItem: FoundItem,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(start = 10.dp, end = 20.dp, top = 20.dp, bottom = 5.dp)
            .clickable {},
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 25.dp
        )
    ) {
        Row(
            modifier = Modifier
        ) {
            val imageURL: String = if (foundItem.image!!.isEmpty()) {
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            } else {
                foundItem.image!!
            }
            LoadImageWithGlide(
                imageUrl = imageURL,
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxSize()
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(0.7f)
            ) {
                Text(
                    text = foundItem.name!!,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .padding(start = 5.dp, top = 3.dp, end = 5.dp)
                )
                Text(
                    text = "Found Where: ${foundItem.foundWhere}",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
                Text(
                    text = "Found When: ${foundItem.date}",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
                Text(
                    text = "${foundItem.uuid}",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
            }
        }
    }
}