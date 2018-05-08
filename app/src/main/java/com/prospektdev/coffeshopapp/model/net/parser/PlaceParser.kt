package com.prospektdev.coffeshopapp.model.net.parser

import com.google.android.gms.maps.model.LatLng
import com.prospektdev.coffeshopapp.model.net.data.Place
import org.json.JSONObject

object PlaceParser : Parser<List<Place>> {
    private const val RESULTS = "results"
    private const val GEOMETRY = "geometry"
    private const val LOCATION = "location"
    private const val LAT = "lat"
    private const val LNG = "lng"
    private const val NAME = "name"
    private const val VICINITY = "vicinity"

    override fun parse(json: String): List<Place> {
        val places = mutableListOf<Place>()
        val rootObject = JSONObject(json)
        val array = rootObject.getJSONArray(RESULTS)
        val length = array.length()
        var i = 0

        while (i < length) {
            val item = array.get(i) as JSONObject
            val place = parsePlace(item)
            places.add(place)
            i++
        }

        return places
    }

    private fun parsePlace(obj: JSONObject): Place {
        val geometry = obj.get(GEOMETRY) as JSONObject
        val jsonLocation = geometry.get(LOCATION) as JSONObject
        val lat = jsonLocation.get(LAT) as Double
        val lng = jsonLocation.get(LNG) as Double
        val name = obj.get(NAME) as String
        val address = obj.get(VICINITY) as String
        val latlng = LatLng(lat, lng)

        return Place(latlng, name, address)
    }
}