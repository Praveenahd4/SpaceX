package com.hd.spacex.data.retrofit

import retrofit2.http.GET


interface APIService{
    @GET("launches")
    suspend fun getRocketLaunchList(): List<RocketLaunch>

}