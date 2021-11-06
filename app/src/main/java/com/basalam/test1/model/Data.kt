package com.basalam.test1.model

import com.google.gson.annotations.SerializedName

data class DataList(@SerializedName("data")
                var data : List<Item>)

data class Item (
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var image: String
    )
