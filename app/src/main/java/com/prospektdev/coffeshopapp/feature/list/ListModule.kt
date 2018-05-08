package com.prospektdev.coffeshopapp.feature.list

import dagger.Binds
import dagger.Module

@Module
interface ListModule {
    @Binds
    fun binds(activity: ListActivity): ListActivity
}