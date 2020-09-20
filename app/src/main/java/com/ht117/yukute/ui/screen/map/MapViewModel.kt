package com.ht117.yukute.ui.screen.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ht117.yukute.location.LocationDataSource
import com.ht117.yukute.location.model.LocationModel
import com.ht117.yukute.ui.screen.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel(private val locationRepository: LocationDataSource) : BaseViewModel() {

    private val _currentLocation = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = _currentLocation

    fun initMap() {
        io.launch {
            val currentLocation = locationRepository.getLocation()

            withContext(ui.coroutineContext) {
                _currentLocation.postValue(currentLocation)
            }
        }
    }
}