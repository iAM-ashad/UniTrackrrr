package com.iamashad.unitrackrrr.screens.fee

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.components.AppTopBar
import com.iamashad.unitrackrrr.components.RadioButtonList
import com.iamashad.unitrackrrr.navigation.TrackrScreens

@Composable
fun FeeScreen(
    navController: NavController
) {
   Column (
       modifier = Modifier
   ) {
       AppTopBar(
           isHome = false,
           navController = navController,
           title = ""
       )
       ChooseSemester(navController)
   }
}

@Composable
fun ChooseSemester(
    navController: NavController,
) {
    val selectedSemester = remember {
        mutableIntStateOf(1)
    }
    Column (
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
    ){
        Text(
            text = "Choose a semester: ",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
        )
        RadioButtonList {selectedValue->
            selectedSemester.intValue = selectedValue
        }
        ViewReceiptsButton(
            selectedSemester = selectedSemester.intValue,
            navController = navController,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
    }
}
@Composable
fun ViewReceiptsButton(
    selectedSemester: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            navController.navigate("${TrackrScreens.VIEWRECEIPTSSCREEN.name}/$selectedSemester")
            Log.d("SEMESTER", "$selectedSemester")
        },
        shape = RoundedCornerShape(corner = CornerSize(25.dp)),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        ),
        modifier = modifier
            .fillMaxWidth(.5f)
    ) {
        Text(
            text = "View Receipts",
            fontSize = 20.sp,
            modifier = modifier
        )
    }
}

