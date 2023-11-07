package com.example.ilabankassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilabankassignment.repository.DataRepository
import com.example.ilabankassignment.util.ResourceCallState
import com.example.ilabankassignment.model.CarouselAndListItemModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CarouselAndListViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private val TAG = CarouselAndListViewModel::class.java.name
    private val _listMutableLiveData =
        MutableLiveData<ResourceCallState<List<CarouselAndListItemModel>>>()
    val listLiveData: LiveData<ResourceCallState<List<CarouselAndListItemModel>>>
        get() = _listMutableLiveData

    init {
        fetchData()
    }

    private fun fetchData() {
        _listMutableLiveData.value = ResourceCallState.loading()
        viewModelScope.launch {
            dataRepository.fetchData().catch {
                Log.d(TAG, "fetchData() error : ${it.message}")
                _listMutableLiveData.value = ResourceCallState.error(it.message.toString())
            }.collect {
                Log.d(TAG, "fetchData() success : ${it.data}")
                _listMutableLiveData.value = ResourceCallState.success(it.data)
            }
        }
    }


}