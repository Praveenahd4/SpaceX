package com.hd.spacex.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hd.spacex.data.retrofit.RocketLaunch
import com.hd.spacex.viewmodel.MainViewModel

@Composable
fun RocketLaunchScreen(
    modifier: Modifier = Modifier,
    viewstate: MainViewModel.LaunchState,
    navigateToDetail: (RocketLaunch) -> Unit
) {
    Box(modifier.fillMaxSize()) {
        when {
            viewstate.error != null -> {
                Text(
                    text = "${viewstate.error}",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            viewstate.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                // Display launchers
                Launchers(launches = viewstate.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun Launchers(launches: List<RocketLaunch>,
              navigateToDetail:(RocketLaunch)->Unit){
    Column {
        Text(
            text = "SpaceX Launches",
            fontSize = 30.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Tap a mission for more details.",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(1f)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(launches) { rocketLaunch ->
                LauncherItem(rocketLaunch = rocketLaunch,navigateToDetail )
            }
        }
    }
}

@Composable
fun LauncherItem(rocketLaunch: RocketLaunch, navigateToDetail:(RocketLaunch)->Unit) {

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
            .clickable {
                navigateToDetail(rocketLaunch)
            }
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffeeeeee))
            .padding(10.dp)

    ) {

        Text(
            text = rocketLaunch.name,
            color = Color.Black,
            fontSize = 18.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
        val (statusText, statusColor) = getLaunchStatus(rocketLaunch.success)
        Text(
            text = statusText,
            color = statusColor
        )

    }
}

fun getLaunchStatus(success: Boolean?): Pair<String, Color>{
    return when{
        success == true -> Pair("Successful", Color.Green)
        success == false -> Pair("Unsuccessful", Color.Red)
        else -> Pair("Upcoming", Color.Blue)
    }
}