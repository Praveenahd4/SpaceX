package com.hd.spacex.data.retrofit

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity
@Parcelize
data class RocketLaunch(
    @PrimaryKey(autoGenerate = true)
    val flightNumber: Int,
    val name: String,
    val date_utc: String,
    val details: String?,
    val success: Boolean?
):Parcelable

