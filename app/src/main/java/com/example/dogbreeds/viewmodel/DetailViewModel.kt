package com.example.dogbreeds.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dogbreeds.model.DogBreed
import com.example.dogbreeds.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): BaseViewModel(application) {

   // val dogLiveData = MutableLiveData<DogBreed>()
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog ?: DogBreed("empty", "e", "e", "e", "e", "e", "")
        }
    }
}