package com.basalam.test1.network

import android.util.Log
import com.basalam.test1.model.DataList
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getItem(): DataList {
        return apiService.getItem("animal")
    }


}