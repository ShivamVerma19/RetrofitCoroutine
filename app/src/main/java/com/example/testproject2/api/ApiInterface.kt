package com.example.testproject2.api

import com.example.testproject2.model.Jokes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/get_memes")
    suspend fun getData() : Response<Jokes>
}