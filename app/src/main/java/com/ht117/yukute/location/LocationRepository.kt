package com.ht117.yukute.location

import com.ht117.yukute.location.model.LocationModel

class LocationRepository(
    val localDataSource: LocationLocalDataSource,
    val remoteDataSource: LocationRemoteDataSource
) : LocationDataSource {

    override suspend fun getLocation(): LocationModel? {
        var location = localDataSource.getLocation()

        if (location == null || location.lat == 0.0 || location.long == 0.0) {
            location = remoteDataSource.getLocation()
        }

        location?.let { saveLocation(location) }
        return location
    }

    override suspend fun saveLocation(location: LocationModel) {
        // for now we only save to local
        localDataSource.saveLocation(location)
    }
}