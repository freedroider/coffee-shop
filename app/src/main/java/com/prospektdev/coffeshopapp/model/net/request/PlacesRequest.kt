package com.prospektdev.coffeshopapp.model.net.request

import android.location.Location
import com.prospektdev.coffeshopapp.BuildConfig
import com.prospektdev.coffeshopapp.model.net.data.Place
import com.prospektdev.coffeshopapp.model.net.parser.Parser
import com.prospektdev.coffeshopapp.model.net.parser.PlaceParser
import java.net.URL

class PlacesRequest private constructor(private val location: Location,
                                        private val radius: Int,
                                        private val type: String,
                                        private val keyword: String) : BaseRequest<List<Place>>() {

    override fun getUrl() = URL("${BuildConfig.PLACES_API_BASE_URL}nearbysearch/json?" +
            "location=${location.latitude},${location.longitude}" +
            "&radius=$radius" +
            "&type=$type" +
            "&keyword=$keyword" +
            "&key=${BuildConfig.PLACES_API_KEY}")


    override val parser: Parser<List<Place>> = PlaceParser

    class Builder {
        private lateinit var location: Location
        private var radius: Int = 10000
        private var type: String = "cafe"
        private var keyword: String = "coffee"

        fun location(location: Location): Builder {
            this.location = location
            return this
        }

        fun radius(radius: Int): Builder {
            this.radius = radius
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun keyword(keyword: String): Builder {
            this.keyword = keyword
            return this
        }

        fun build() = PlacesRequest(location, radius, type, keyword)
    }
}
