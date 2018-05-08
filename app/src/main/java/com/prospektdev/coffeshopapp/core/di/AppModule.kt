package com.prospektdev.coffeshopapp.core.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
interface AppModule {
    @Singleton
    @Binds
    fun provideContext(app: Application): Context
}