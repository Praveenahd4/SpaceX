package com.hd.spacex.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hd.spacex.data.retrofit.RocketLaunch

@Composable
fun LaunchDetailScreen(launch: RocketLaunch) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Launch Name
        Text(
            text = launch.name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        // Launch Status
        val (statusText) = getLaunchStatus(launch.success)
        Text(
            text = statusText,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        // Launch Date
        Text(
            text = launch.date_utc,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Launch Details
        Text(
            text = launch.details ?: "No details available.",
            fontSize = 14.sp,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 4.dp),
            textAlign = TextAlign.Justify,
            //color = Color.
        )

    }
}

