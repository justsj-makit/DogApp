package dev.mitigate.dogapp

import android.app.Application
import timber.log.Timber
import timber.log.Timber.Forest.plant


class DogApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
    }
}