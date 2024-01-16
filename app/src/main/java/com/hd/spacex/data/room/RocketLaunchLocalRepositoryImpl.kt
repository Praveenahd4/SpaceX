package com.hd.spacex.data.room

import com.hd.spacex.data.retrofit.RocketLaunch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketLaunchLocalRepositoryImpl @Inject constructor(private val rocketLaunchDao: RocketLaunchDao) :
    RocketLaunchLocalRepository {

    override suspend fun insertRocket(launch: RocketLaunch) {
        rocketLaunchDao.insertRocket(launch)
    }

    override fun getRocketLaunchFromDB(): Flow<List<RocketLaunch>> {
        return rocketLaunchDao.getRocketLaunchFromDB()
    }
}