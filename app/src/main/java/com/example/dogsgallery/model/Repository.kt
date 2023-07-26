package com.example.dogsgallery.model

import androidx.annotation.WorkerThread
import com.example.dogsgallery.model.retrofit.ResponseDog
import com.example.dogsgallery.model.retrofit.ResponseListBreeds
import com.example.dogsgallery.model.retrofit.RetrofitInstance
import com.example.dogsgallery.model.retrofit.RetrofitInstanceBreed
import com.example.dogsgallery.model.room.Dog
import com.example.dogsgallery.model.room.DogsDao
import kotlinx.coroutines.flow.Flow


class DogRepository (private val dao :DogsDao){
    private val dogService = RetrofitInstance.Service
    private val breedDogService = RetrofitInstanceBreed.Service

    suspend fun getDogs(numberDogs: String): ResponseDog {
        return dogService.getDogs(numberDogs)
    }
    suspend fun getDogsByBreed(breed:String,numberDogs:String): ResponseDog {
        return breedDogService.getDogsByBreed(breed,numberDogs)
    }
    suspend fun getDogsBySubBreed(breed:String,subBreed:String,numberDogs:String): ResponseDog {
        return breedDogService.getDogsBySubBreed(breed,subBreed,numberDogs)
    }
    suspend fun getAllBreed(): ResponseListBreeds {
        return dogService.getAllBreeds()
    }

    @WorkerThread
    fun getAllDogs(): Flow<List<Dog>> {
        return dao.getAll()
    }

    @WorkerThread
    fun insertDog(dog: Dog){
        dao.insert(dog)
    }

}
