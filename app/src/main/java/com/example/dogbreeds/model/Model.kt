package com.example.dogbreeds.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data from https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json
 */
@Entity //possible to set table name: @Entity(tableName = "name")
data class DogBreed (
    @ColumnInfo(name = "breed_id")
    //if variable name matches name from JSON, no annotation is necessary
    @SerializedName("id")
    val breedId: String?,

    @ColumnInfo(name = "dog_name")
    @SerializedName("name")
    val dogBreed: String?,

    @ColumnInfo(name = "dog_span")
    @SerializedName("life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val breedGroup: String?,

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val bredFor: String?,

    @SerializedName("temperament")
    val temperament: String?,

    @ColumnInfo(name = "dog_url")
    @SerializedName("url")
    val imageUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

data class DogPalette(var color: Int)

data class SmsInfo(
    var to: String,
    var text: String,
    var imageUrl: String?
)