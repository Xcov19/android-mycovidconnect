package com.ht117.data.location

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.ht117.domain.location.LocationDataSource
import com.ht117.domain.location.model.LocationModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationRemoteDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): LocationModel? = suspendCoroutine { completable ->
        fusedLocationClient.lastLocation
            .addOnCanceledListener {
                completable.resumeWithException(Exception("Unable to get location"))
            }
            .addOnSuccessListener {
                val locationModel = it.toLocationModel()
                completable.resume(locationModel)
            }
    }

    override suspend fun saveLocation(location: LocationModel) {
    }
}