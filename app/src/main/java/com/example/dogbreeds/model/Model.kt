package com.example.dogbreeds.model

import com.google.gson.annotations.SerializedName

/**
 * Data from https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json
 */
data class DogBreed (
    //if variable name matches name from JSON, no annotation is necessary
    @SerializedName("id")
    val breedId: String?,
    @SerializedName("name")
    val dogBreed: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imageUrl: String?
)