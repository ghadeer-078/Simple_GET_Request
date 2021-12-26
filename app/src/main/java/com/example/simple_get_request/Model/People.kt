package com.example.simple_get_request.Model

import com.google.gson.annotations.SerializedName


data class People(

    var data: List<PeopleDetailed>? = null
)


data class PeopleDetailed(

    @SerializedName("name")
    var name: String? = null
)