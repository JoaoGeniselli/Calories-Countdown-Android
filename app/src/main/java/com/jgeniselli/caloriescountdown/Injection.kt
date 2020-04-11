package com.jgeniselli.caloriescountdown

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Injection {
    val module by lazy {
        module {
            viewModel { MainViewModel(Repository()) }
        }
    }
}