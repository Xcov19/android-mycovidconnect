package com.ht117.yukute.ui.screen.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.ht117.yukute.location.model.LocationModel
import com.ht117.yukute.R
import com.ht117.yukute.ui.screen.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment(R.layout.fragment_map), OnMapReadyCallback {

    private var map: GoogleMap? = null
    private val mapViewModel: MapViewModel by viewModel()
    private var currentLocationMarker: Marker? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mapView) {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }

        mapViewModel.currentLocation.observe(viewLifecycleOwner, Observer {
            it?.let { showLocationOnMap(it) }
        })
    }

    private fun showLocationOnMap(locationModel: LocationModel) {
        currentLocationMarker?.let { it.remove() }

        //Place current location marker
        val latLng = LatLng(locationModel.lat, locationModel.long)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        currentLocationMarker = map?.addMarker(markerOptions)

        //move map camera

        //move map camera
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        if (mapView != null) {
            mapView.onDestroy()
        }
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(gMap: GoogleMap?) {
        map = gMap
        mapViewModel.initMap()
    }
}