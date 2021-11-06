package com.basalam.test1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basalam.test1.repository.MainRepository
import com.basalam.test1.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val itemStateFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _itemStateFlow:StateFlow<ApiState> = itemStateFlow

    fun getItem() = viewModelScope.launch {
        itemStateFlow.value = ApiState.Loading
        mainRepository.getItem()
            .catch { e->
                itemStateFlow.value= ApiState.Failure(e)
            }.collect { data->

                itemStateFlow.value= ApiState.Success(data)
            }
    }
}