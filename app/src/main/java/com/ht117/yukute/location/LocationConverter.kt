package com.ht117.yukute.location

import android.location.Location
import com.ht117.yukute.location.model.LocationModel

fun Location.toLocationModel(): LocationModel {
    return LocationModel(latitude, longitude)
}