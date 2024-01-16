package com.hd.spacex.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hd.spacex.data.retrofit.RocketLaunch
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketLaunchDao {

    @Insert
    suspend fun insertRocket(launch: RocketLaunch)

    @Query("SELECT * FROM rocketlaunch")
    fun getRocketLaunchFromDB(): Flow<List<RocketLaunch>>
}