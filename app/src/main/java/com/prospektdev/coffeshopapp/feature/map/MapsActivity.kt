package com.prospektdev.coffeshopapp.feature.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.prospektdev.coffeshopapp.R
import com.prospektdev.coffeshopapp.util.Starter
import com.google.android.gms.maps.model.CameraPosition
import com.prospektdev.coffeshopapp.core.base.BaseActivity

class MapsActivity : BaseActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onLocationEnabled() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun obtainLayoutId(): Int = R.layout.activity_maps

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val coffeeShop = getLatLng()
        val cameraPosition = CameraPosition.Builder()
                .target(coffeeShop)      // Sets the center of the map to Mountain View
                .zoom(13f)               // Sets the zoom
                .bearing(90f)            // Sets the orientation of the camera to east
                .tilt(30f)               // Sets the tilt of the camera to 30 degrees
                .build()
        map.addMarker(MarkerOptions().position(coffeeShop).title(getMarkerTitle()))
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun getLatLng(): LatLng {
        val lat = intent.getDoubleExtra(Starter.INTENT_KEY_LAT, 0.0)
        val lng = intent.getDoubleExtra(Starter.INTENT_KEY_LNG, 0.0)
        return LatLng(lat, lng)
    }

    private fun getMarkerTitle(): String? = intent.getStringExtra(Starter.INTENT_KEY_NAME)
}
