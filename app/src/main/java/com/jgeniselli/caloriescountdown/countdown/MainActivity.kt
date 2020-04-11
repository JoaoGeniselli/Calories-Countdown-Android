package com.jgeniselli.caloriescountdown.countdown

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jgeniselli.caloriescountdown.R
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == R.id.edit_goal) {
                openGoalEditor()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun openGoalEditor() {

    }
}
