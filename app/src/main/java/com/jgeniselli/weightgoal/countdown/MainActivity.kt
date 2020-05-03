package com.jgeniselli.weightgoal.countdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jgeniselli.weightgoal.R
import com.jgeniselli.weightgoal.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        binding.viewModel = viewModel

        viewModel.clearFocus.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                // TODO: IMPLEMENT
            }
        })
    }
}