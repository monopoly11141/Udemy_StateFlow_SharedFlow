package com.example.udemy_stateflow_sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(startingTotal: Int) : ViewModel() {

//    private var total = MutableLiveData<Int>()
//
//    val totalLiveData : LiveData<Int>
//        get() = total

    private val _flowTotal = MutableStateFlow<Int>(0)
    val flowTotal: StateFlow<Int> = _flowTotal

    init {
        //total.value = startingTotal
        _flowTotal.value = startingTotal
    }

    fun setTotal(input: Int) {
        //total.value = total.value?.plus(input)
        _flowTotal.value = _flowTotal.value.plus(input)

    }

}