package com.jgeniselli.caloriescountdown

import android.app.Application
import org.koin.core.context.startKoin

class CaloriesCountdownApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(Injection.module)
        }
    }
}