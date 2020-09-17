package com.ht117.domain.location

import com.ht117.domain.location.model.LocationModel


interface LocationDataSource {
    suspend fun getLocation(): LocationModel?
    suspend fun saveLocation(location: LocationModel)
}