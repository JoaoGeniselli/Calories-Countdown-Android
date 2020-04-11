package com.jgeniselli.caloriescountdown.startup

import com.jgeniselli.caloriescountdown.countdown.MainViewModel
import com.jgeniselli.caloriescountdown.storage.CaloriesGoalRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyInjection {
    val module by lazy {
        module {
            viewModel {
                MainViewModel(
                    CaloriesGoalRepository()
                )
            }
        }
    }
}