package com.jgeniselli.caloriescountdown.startup

import android.app.Application
import org.koin.core.context.startKoin

class CaloriesCountdownApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DependencyInjection.module)
        }
    }
}