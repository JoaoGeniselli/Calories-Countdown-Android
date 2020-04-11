package com.jgeniselli.caloriescountdown.countdown

import androidx.lifecycle.*
import com.jgeniselli.caloriescountdown.storage.CaloriesGoalRepository
import com.jgeniselli.caloriescountdown.toolbox.livedata.Event
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CaloriesGoalRepository
) : ViewModel(), LifecycleObserver {

    private val _basalMetabolicRate = MutableLiveData(0)
    private val _remainingCalories = MutableLiveData<String>()
    private val _totalToBeSubtracted = MutableLiveData(0)
    private val _subtractButtonEnabled = MutableLiveData(false)
    private val _clearFocus = MutableLiveData<Event<Unit>>()

    val feedingCaloriesText = MutableLiveData<String>()
    val physicalActivitiesCaloriesText = MutableLiveData<String>()

    val basalMetabolicRate: LiveData<Int> get() = _basalMetabolicRate
    val remainingCaloriesText: LiveData<String> get() = _remainingCalories
    val totalToBeSubtracted: LiveData<Int> get() = _totalToBeSubtracted
    val subtractButtonEnabled: LiveData<Boolean> get() = _subtractButtonEnabled
    val clearFocus: LiveData<Event<Unit>> get() = _clearFocus

    private val feedingCalories: Int?
        get() = feedingCaloriesText.value?.toIntOrNull()

    private val physicalActivitiesCalories: Int
        get() = physicalActivitiesCaloriesText.value?.toIntOrNull() ?: 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        viewModelScope.launch {
            _remainingCalories.value = repository.getRemainingCalories().toString()
            _basalMetabolicRate.value = repository.getBasalMetabolicRate()
            _totalToBeSubtracted.value = 0
            _subtractButtonEnabled.value = false
        }
    }

    fun onInputChanged() {
        updateSubtractButton()
        updateTotalToBeSubtracted()
    }

    private fun updateSubtractButton() {
        val feedingCalories = this.feedingCaloriesText.value?.toIntOrNull()
        _subtractButtonEnabled.value = feedingCalories != null
    }

    private fun updateTotalToBeSubtracted() {
        val feedingCalories = this.feedingCalories ?: return
        val caloriesToSubtract = repository.calculateSubtraction(
            feedingCalories,
            physicalActivitiesCalories
        )
        _totalToBeSubtracted.value = caloriesToSubtract
    }

    fun subtractCalories() {
        val feedingCalories = this.feedingCalories ?: return
        viewModelScope.launch {
            val updatedGoal = repository.subtract(feedingCalories, physicalActivitiesCalories)
            _remainingCalories.value = updatedGoal.toString()
            feedingCaloriesText.value = ""
            physicalActivitiesCaloriesText.value = ""
            _clearFocus.value = Event(Unit)
            _totalToBeSubtracted.value = 0
        }
    }
}