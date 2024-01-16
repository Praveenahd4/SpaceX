package com.hd.spacex.di

import android.app.Application
import androidx.room.Room
import com.hd.spacex.data.room.RocketLaunchDatabase
import com.hd.spacex.data.room.RocketLaunchLocalRepository
import com.hd.spacex.data.room.RocketLaunchLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton

    fun provideRocketLaunchDatabase(app:Application):RocketLaunchDatabase{
        return Room.databaseBuilder(
            app,
            RocketLaunchDatabase::class.java,
            "rocket_launch_data_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRocketLaunchRoomRepository(db:RocketLaunchDatabase): RocketLaunchLocalRepository{
        return RocketLaunchLocalRepositoryImpl(db.rocketLaunchDao())
    }
}