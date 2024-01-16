package com.hd.spacex.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hd.spacex.data.room.RocketLaunchLocalRepository
import com.hd.spacex.data.retrofit.RocketLaunchApiRepository
import com.hd.spacex.data.retrofit.RocketLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rocketLaunchApiRepository: RocketLaunchApiRepository,
    private val rocketLaunchLocalRepository: RocketLaunchLocalRepository
): ViewModel()  {

    private val _launcherState = mutableStateOf(LaunchState())
    val launcherState: State<LaunchState> = _launcherState

    init {
        fetchLaunch()
    }

    private fun fetchLaunch(){

        val timeoutDurationMillis = 10000L

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Fetch data from remote API

                val response = withTimeout(timeoutDurationMillis) {
                    rocketLaunchApiRepository.getRocketLaunch()
                }

                // Save data to Room database
                response.forEach { launch ->
                    rocketLaunchLocalRepository.insertRocket(launch)
                }

                // Update state
                _launcherState.value = _launcherState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            }catch (timeoutException: TimeoutCancellationException){
                _launcherState.value = _launcherState.value.copy(
                    loading = false,
                    error = "Timeout: Unable to fetch data. Please check your internet connection and try again.",
                )

            } catch (e: Exception) {
                // Handle the case when both network and local fetch fail
                viewModelScope.launch(Dispatchers.Main) {
                    try {
                        val localLaunches = rocketLaunchLocalRepository.getRocketLaunchFromDB().firstOrNull()
                        if (localLaunches.isNullOrEmpty()) {
                            _launcherState.value = _launcherState.value.copy(
                                loading = false,
                                error = "Turn on Internet and try again!!",
                            )
                        } else {
                            _launcherState.value = _launcherState.value.copy(
                                list = localLaunches,
                                loading = false,
                                error = null
                            )
                        }
                    } catch (localException: Exception) {
                        // Handle the case when both network and local fetch fail
                        _launcherState.value = _launcherState.value.copy(
                            loading = false,
                            error = "Error fetching Launch List from local database: ${localException.message}",
                        )
                    }
                }
            }
        }
    }

    data class LaunchState(
        val error: String? = null,
        val loading:Boolean = true,
        val list: List<RocketLaunch> = emptyList()

    )
}