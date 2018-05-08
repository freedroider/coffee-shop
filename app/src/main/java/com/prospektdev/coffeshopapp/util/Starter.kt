package com.prospektdev.coffeshopapp.util

import android.content.Context
import android.content.Intent
import com.prospektdev.coffeshopapp.feature.map.MapsActivity
import com.prospektdev.coffeshopapp.model.net.data.Place

object Starter {
    const val INTENT_KEY_LAT = "com.prospektdev.coffeshopapp.lat"
    const val INTENT_KEY_LNG = "com.prospektdev.coffeshopapp.lng"
    const val INTENT_KEY_NAME = "com.prospektdev.coffeshopapp.name"

    fun startMapActivity(context: Context, place: Place) {
        val intent = Intent(context, MapsActivity::class.java)
        intent.putExtra(INTENT_KEY_LAT, place.latLng.latitude)
        intent.putExtra(INTENT_KEY_LNG, place.latLng.longitude)
        intent.putExtra(INTENT_KEY_NAME, place.name)
        context.startActivity(intent)
    }

    fun startLocationSettings(context: Context) {
        val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }
}