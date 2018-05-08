package com.prospektdev.coffeshopapp.core.di

import com.prospektdev.coffeshopapp.core.App

object AppInjector {
    fun inject(app: App) {
        DaggerAppComponent.builder()
                .application(app)
                .build()
                .inject(app)
    }
}