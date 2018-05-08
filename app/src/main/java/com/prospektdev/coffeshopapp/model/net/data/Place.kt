package com.prospektdev.coffeshopapp.model.net.data

import com.google.android.gms.maps.model.LatLng

data class Place(val latLng: LatLng,
                 val name: String,
                 val address: String)