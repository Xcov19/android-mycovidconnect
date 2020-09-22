package com.ht117.yukute.data.local

import android.content.SharedPreferences
import com.ht117.yukute.data.LocationDataSource
import com.ht117.yukute.location.model.LocationModel

class LocationLocalDataSource(val prefs: SharedPreferences) : LocationDataSource {

    private val KEY_LAT = "latitude"
    private val KEY_LON = "longitude"

    override suspend fun getLocation(): LocationModel? {

        val lat = prefs.getString(KEY_LAT, "NaN")
        val lon = prefs.getString(KEY_LON, "NaN")

        return try {
            LocationModel(lat?.toDouble()!!, lon?.toDouble()!!)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveLocation(location: LocationModel) {
        val editor = prefs.edit()
        editor.putString(KEY_LAT, location.lat.toString())
        editor.putString(KEY_LON, location.long.toString())
        editor.apply()
    }
}