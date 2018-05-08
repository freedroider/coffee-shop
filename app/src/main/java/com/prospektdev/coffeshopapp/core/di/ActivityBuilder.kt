package com.prospektdev.coffeshopapp.core.di

import com.prospektdev.coffeshopapp.feature.list.ListActivity
import com.prospektdev.coffeshopapp.feature.list.ListModule
import com.prospektdev.coffeshopapp.feature.map.MapsModule
import com.prospektdev.coffeshopapp.feature.map.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [ListModule::class])
    fun bindListActivityInjector(): ListActivity

    @ContributesAndroidInjector(modules = [MapsModule::class])
    fun bindMapsActivityInjector(): MapsActivity
}