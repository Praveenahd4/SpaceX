package com.hd.spacex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hd.spacex.data.room.RocketLaunchLocalRepository
import com.hd.spacex.data.retrofit.RocketLaunchApiRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val rocketLaunchApiRepository: RocketLaunchApiRepository,
    private val rocketLaunchLocalRepository: RocketLaunchLocalRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(rocketLaunchApiRepository, rocketLaunchLocalRepository) as T
}