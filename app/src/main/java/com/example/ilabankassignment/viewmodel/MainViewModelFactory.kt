package com.example.ilabankassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilabankassignment.repository.DataRepository

class MainViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CarouselAndListViewModel::class.java)) {
            CarouselAndListViewModel(this.dataRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}