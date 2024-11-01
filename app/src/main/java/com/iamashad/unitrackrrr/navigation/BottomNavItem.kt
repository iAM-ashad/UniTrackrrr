package com.iamashad.unitrackrrr.navigation

import com.iamashad.unitrackrrr.R

sealed class BottomNavItem(
    val label: String,
    val icon: Int,
    val route: String
) {
    object Home: BottomNavItem("Home", R.drawable.home, TrackrScreens.HOMESCREEN.name)
    object ViewFound: BottomNavItem("ViewFound", R.drawable.found, TrackrScreens.VIEWFOUNDSCREEN.name)
    object ViewLost: BottomNavItem("View Lost", R.drawable.lost, TrackrScreens.VIEWLOSTSCREEN.name)
    object Fee: BottomNavItem("Fee", R.drawable.money, TrackrScreens.FEESCREEN.name)
    object AddLost: BottomNavItem("Home", R.drawable.plus, TrackrScreens.ADDTOLOSTSCREEN.name)
}
