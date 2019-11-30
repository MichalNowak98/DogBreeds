package com.example.dogbreeds.model

import io.reactivex.Single
import retrofit2.http.GET

interface DogsApi {
    // endpoint: "DevTides/DogsApi/master/dogs.json"
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogBreed>>
}