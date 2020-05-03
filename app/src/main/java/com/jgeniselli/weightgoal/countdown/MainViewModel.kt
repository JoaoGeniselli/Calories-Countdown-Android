package com.jgeniselli.weightgoal.countdown

import androidx.lifecycle.*
import com.jgeniselli.weightgoal.storage.Repository
import com.jgeniselli.weightgoal.toolbox.livedata.Event
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel(), LifecycleObserver {

    private val _remainingKilograms = MutableLiveData<String>()
    private val _subtractButtonEnabled = MutableLiveData(false)
    private val _clearFocus = MutableLiveData<Event<Unit>>()

    val remainingKilograms: LiveData<String> get() = _remainingKilograms
    val subtractButtonEnabled: LiveData<Boolean> get() = _subtractButtonEnabled
    val clearFocus: LiveData<Event<Unit>> get() = _clearFocus

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        viewModelScope.launch {
            _remainingKilograms.value = repository.getRemainingKilograms().toString()
            _subtractButtonEnabled.value = false
        }
    }
}