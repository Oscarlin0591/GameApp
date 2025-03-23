package com.example.gameapp.api


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInterface {

@GET("games")
    suspend fun getGames() : Response<ArrayList<Games>> //suspendable means method can "suspend" and stop function for some time.

// this set of functions is common for all apps using online data via retrofit.
    companion object{ // one time object or singleton
        var BASE_URL = "https://www.freetogame.com/api/"

        fun create(): ApiInterface { // creates an instance of the retrofit class
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
