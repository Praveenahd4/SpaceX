package com.hd.spacex.ui.theme

sealed class Screen(val route:String) {
    object RocketLaunchScreen: Screen("rocketlaunchscreen")
    object DetailScreen: Screen("detailscreen")
}