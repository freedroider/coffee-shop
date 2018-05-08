package com.prospektdev.coffeshopapp.model.location

import android.location.Location
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.prospektdev.coffeshopapp.util.Logger

abstract class LocationParser : LocationCallback() {
    override fun onLocationResult(result: LocationResult?) {
        Logger.e("LocCallback: onLocationResult: ${result.toString()}")
        if (result != null) {
            locationResult(result.lastLocation)
        }
    }

    override fun onLocationAvailability(p0: LocationAvailability?) {
        Logger.e("LocCallback onLocationAvailability: ${p0.toString()}")
    }

    abstract fun locationResult(result: Location)
}
