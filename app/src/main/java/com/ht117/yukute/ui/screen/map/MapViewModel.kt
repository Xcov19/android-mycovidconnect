package com.ht117.yukute.ui.screen.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ht117.yukute.data.LocationDataSource
import com.ht117.yukute.location.model.LocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel(private val locationRepository: LocationDataSource) : ViewModel() {

    private val _currentLocation = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = _currentLocation

    fun initMap() {
        viewModelScope.launch {
            val currentLocation = locationRepository.getLocation()

            withContext(Dispatchers.Main) {
                _currentLocation.postValue(currentLocation)
            }
        }
    }
}