package com.basalam.test1.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basalam.test1.model.CompleteItem
import com.basalam.test1.repository.MainRepository

class MainViewModel : ViewModel(){

    var list : MutableLiveData<List<CompleteItem>> = MutableLiveData()

    fun getItem() : LiveData<List<CompleteItem>> {
        list = MainRepository.getServiceApiCall()
        return list
    }
}