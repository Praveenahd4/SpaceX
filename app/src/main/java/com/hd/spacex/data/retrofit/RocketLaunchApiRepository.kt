package com.hd.spacex.data.retrofit

import javax.inject.Inject

class RocketLaunchApiRepository@Inject constructor(private val apiService: APIService) {

    suspend fun getRocketLaunch(): List<RocketLaunch> {
        return apiService.getRocketLaunchList()
    }
}
