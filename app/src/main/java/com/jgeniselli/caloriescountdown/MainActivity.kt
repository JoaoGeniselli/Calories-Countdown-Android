package com.jgeniselli.caloriescountdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jgeniselli.caloriescountdown.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.form_subtract_calories.*
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
                input_feeding_calories.clearFocus()
                input_physical_activities_calories.clearFocus()
            }
        })
    }
}
