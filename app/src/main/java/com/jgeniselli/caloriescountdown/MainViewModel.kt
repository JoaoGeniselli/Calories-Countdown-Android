package com.jgeniselli.caloriescountdown

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel(), LifecycleObserver {

    private val _basalMetabolicRate = MutableLiveData<String>()
    private val _remainingCalories = MutableLiveData<String>()
    private val _totalToBeSubtracted = MutableLiveData<String>()
    private val _subtractButtonEnabled = MutableLiveData<Boolean>()
    private val _clearFocus = MutableLiveData<Event<Unit>>()

    val feedingCaloriesText = MutableLiveData<String>()
    val physicalActivitiesCaloriesText = MutableLiveData<String>()

    val basalMetabolicRateText: LiveData<String> get() = _basalMetabolicRate
    val remainingCaloriesText: LiveData<String> get() = _remainingCalories
    val totalToBeSubtractedText: LiveData<String> get() = _totalToBeSubtracted
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
            _basalMetabolicRate.value = repository.getBasalMetabolicRate().toString()
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
        _totalToBeSubtracted.value = caloriesToSubtract.toString()
    }

    fun subtractCalories() {
        val feedingCalories = this.feedingCalories ?: return
        viewModelScope.launch {
            val updatedGoal = repository.subtract(feedingCalories, physicalActivitiesCalories)
            _remainingCalories.value = updatedGoal.toString()
            feedingCaloriesText.value = ""
            physicalActivitiesCaloriesText.value = ""
            _clearFocus.value = Event(Unit)
        }
    }
}