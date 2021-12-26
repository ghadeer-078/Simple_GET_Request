package com.example.simple_get_request.API

import com.example.simple_get_request.Model.PeopleDetailed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/people/")
    fun getUser(): Call<List<PeopleDetailed>>
}