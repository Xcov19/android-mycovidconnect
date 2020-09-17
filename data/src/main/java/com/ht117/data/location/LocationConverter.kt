package com.ht117.data.location

import android.location.Location
import com.ht117.domain.location.model.LocationModel

fun Location.toLocationModel(): LocationModel {
    return LocationModel(latitude, longitude)
}