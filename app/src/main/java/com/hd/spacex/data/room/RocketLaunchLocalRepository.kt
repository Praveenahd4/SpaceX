package com.hd.spacex.data.room

import com.hd.spacex.data.retrofit.RocketLaunch
import kotlinx.coroutines.flow.Flow

// Local repository interface
interface RocketLaunchLocalRepository {
    suspend fun insertRocket(launch: RocketLaunch)

    fun getRocketLaunchFromDB(): Flow<List<RocketLaunch>>
}
