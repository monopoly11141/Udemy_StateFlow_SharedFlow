package com.example.udemy_stateflow_sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(private val startingTotal : Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("unKnown ViewModel class")
    }


}
