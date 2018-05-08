package com.prospektdev.coffeshopapp.feature.map

import dagger.Binds
import dagger.Module

@Module
interface MapsModule {
    @Binds
    fun binds(activity: MapsActivity): MapsActivity
}