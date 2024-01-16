package com.hd.spacex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hd.spacex.compose.LaunchDetailScreen
import com.hd.spacex.compose.RocketLaunchScreen
import com.hd.spacex.data.retrofit.RocketLaunch
import com.hd.spacex.ui.theme.Screen
import com.hd.spacex.viewmodel.MainViewModel
import com.hd.spacex.viewmodel.MainViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun SpaceXNav(
    navController: NavHostController, SpaceXAppViewModel: MainViewModel = hiltViewModel()
) {
    val viewstate by SpaceXAppViewModel.launcherState

    NavHost(navController = navController, startDestination = Screen.RocketLaunchScreen.route) {
        composable(route = Screen.RocketLaunchScreen.route) {
            RocketLaunchScreen(viewstate = viewstate, navigateToDetail = {

                navController.currentBackStackEntry?.savedStateHandle?.set("rocket", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val rocketlaunch =
                navController.previousBackStackEntry?.savedStateHandle?.get<RocketLaunch>("rocket")
                    ?: RocketLaunch(0, "", "", "", null)
            LaunchDetailScreen(launch = rocketlaunch)
        }
    }
}