package com.example.aula_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private var textWelcome = MutableLiveData<String>()

    init {
        textWelcome.value = "Olá"
    }

    fun welcome(): LiveData<String>{
        return textWelcome
    }
}