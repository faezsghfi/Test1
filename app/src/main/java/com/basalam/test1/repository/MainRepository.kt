package com.basalam.test1.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.basalam.test1.model.CompleteItem
import com.basalam.test1.model.DataList
import com.basalam.test1.network.RetrofitClient
import com.basalam.test1.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody




object MainRepository {

    var animalData :List<Item> = ArrayList()
    var flowerData :List<Item> = ArrayList()
    var resultList : MutableLiveData<List<CompleteItem>> = MutableLiveData()

    fun getServiceApiCall() : MutableLiveData<List<CompleteItem>>{

        val tempList : ArrayList<CompleteItem> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resOne = async { RetrofitClient.apiInterface.getItem("animal") }
                val resTwo = async { RetrofitClient.apiInterface.getItem("flower") }
                if (resOne.await().isSuccessful && resTwo.await().isSuccessful) {
                    resOne.await().body()?.let {
                        resTwo.await().body()?.let { it1 ->
                            animalData = it.data
                            flowerData = it1.data
                            for (item in animalData.zip(flowerData))
                            {
                                val completeItem = CompleteItem(item.first.id ,item.first.name , item.first.image , item.second.name , item.second.image)
                                tempList.add(completeItem)
                            }
                        }
                    }
                } else {

                }

                resultList.postValue(tempList)
            } catch (exception: Exception) {
                Log.e(TAG, exception.message!!)
            }
        }
        return resultList
    }


}
