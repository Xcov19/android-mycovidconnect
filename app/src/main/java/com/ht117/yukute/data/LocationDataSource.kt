package com.ht117.yukute.data

import com.ht117.yukute.location.model.LocationModel


interface LocationDataSource {
    suspend fun getLocation(): LocationModel?
    suspend fun saveLocation(location: LocationModel)
}