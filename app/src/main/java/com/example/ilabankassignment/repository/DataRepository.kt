package com.example.ilabankassignment.repository

import com.example.ilabankassignment.model.CarouselAndListItemModel
import com.example.ilabankassignment.util.ResourceCallState
import com.example.ilabankassignment.util.getLocalDataArrayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepository {

    fun fetchData(): Flow<ResourceCallState<List<CarouselAndListItemModel>>> =
        flow {
            emit(
                ResourceCallState.success(
                    getLocalDataArrayList()
                )
            )
        }.flowOn(Dispatchers.IO)
}