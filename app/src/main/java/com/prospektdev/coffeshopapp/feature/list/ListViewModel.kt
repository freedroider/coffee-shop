package com.prospektdev.coffeshopapp.feature.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.location.Location
import android.support.v7.app.AppCompatActivity
import com.prospektdev.coffeshopapp.model.location.LocationLiveData
import com.prospektdev.coffeshopapp.model.net.request.PlacesRequest
import com.prospektdev.coffeshopapp.model.net.data.Place
import javax.inject.Inject

class ListViewModel @Inject constructor() : ViewModel() {
    private var locationLiveData: LocationLiveData? = null
    private var placesList: MutableLiveData<List<Place>>? = null
    private val locationObserver: Observer<Location> = initObserver()

    fun observeLocation(context: AppCompatActivity) {
        if (locationLiveData == null) {
            locationLiveData = LocationLiveData(context)
            locationLiveData?.observe(context, locationObserver)
        }
    }

    fun observeList(context: AppCompatActivity, observer: Observer<List<Place>>) {
        if (placesList == null) {
            placesList = MutableLiveData()
        }
        placesList?.observe(context, observer)
    }

    private fun initObserver(): Observer<Location> = Observer {
        PlacesRequest.Builder()
                .location(it!!)
                .build()
                .execute { placesList?.postValue(it) }
    }
}