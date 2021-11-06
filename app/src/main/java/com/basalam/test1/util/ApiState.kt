package com.basalam.test1.util

import com.basalam.test1.model.Item

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Item>) : ApiState()
    object Empty : ApiState()
}
