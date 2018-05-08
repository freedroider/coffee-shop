package com.prospektdev.coffeshopapp.model.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.os.Looper
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.prospektdev.coffeshopapp.util.Logger
import com.google.android.gms.location.LocationRequest

class LocationLiveData constructor(context: Context) : LiveData<Location>(),
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private val googleApiClient: GoogleApiClient = createGoogleApiClient(context)
    private val locationClient: FusedLocationProviderClient = getLocationClient(context)
    private val parser: LocationParser = object : LocationParser() {
        override fun locationResult(result: Location) {
            postValue(result)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(bundle: Bundle?) {
        Logger.e("GoogleApiClient connected")
        locationClient.lastLocation.addOnSuccessListener { value = it }
        if (hasActiveObservers() && googleApiClient.isConnected) {
            val locationRequest = LocationRequest.create()
            locationClient.requestLocationUpdates(locationRequest, parser, Looper.getMainLooper())
        }
    }

    override fun onConnectionSuspended(cause: Int) {
        Logger.e("GoogleApiClient suspended with casuse:$cause")
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        Logger.e("GoogleApiClient failed with message:${result.errorMessage}")
    }

    override fun onActive() {
        googleApiClient.connect()
    }

    override fun onInactive() {
        if (googleApiClient.isConnected) {
            locationClient.removeLocationUpdates(parser)
        }
        googleApiClient.disconnect()
    }

    private fun getLocationClient(context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

    private fun createGoogleApiClient(context: Context): GoogleApiClient =
            GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build()
}