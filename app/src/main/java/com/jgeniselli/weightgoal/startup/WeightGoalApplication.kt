package com.jgeniselli.weightgoal.startup

import android.app.Application
import org.koin.core.context.startKoin

class WeightGoalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DependencyInjection.module)
        }
    }
}