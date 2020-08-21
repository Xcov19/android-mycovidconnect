package com.ht117.yukute.ui.screen.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.ht117.yukute.R
import com.ht117.yukute.ui.screen.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment: BaseFragment(R.layout.fragment_map), OnMapReadyCallback {

    private var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mapView) {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
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
    }
}