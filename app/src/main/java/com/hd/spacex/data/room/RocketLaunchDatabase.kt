package com.hd.spacex.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hd.spacex.data.retrofit.RocketLaunch

@Database(entities = [RocketLaunch::class], version=1, exportSchema = false)
abstract class RocketLaunchDatabase: RoomDatabase() {
    abstract fun rocketLaunchDao(): RocketLaunchDao
}