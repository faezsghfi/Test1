package com.basalam.test1.repository

import android.util.Log
import com.basalam.test1.model.Item
import com.basalam.test1.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getItem():Flow<List<Item>> = flow {
        emit(apiServiceImpl.getItem().data)
    }.flowOn(Dispatchers.IO)

}