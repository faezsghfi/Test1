package com.basalam.test1.network

import com.basalam.test1.model.DataList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("intern.android/")
   suspend fun getItem(@Query("kind") kind: String): DataList

}