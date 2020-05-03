package com.jgeniselli.weightgoal.startup

import com.jgeniselli.weightgoal.countdown.MainViewModel
import com.jgeniselli.weightgoal.storage.CaloriesGoalRepository
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