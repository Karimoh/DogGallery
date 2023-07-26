package com.example.dogsgallery.model.retrofit

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val URL_BASE = "https://dog.ceo/api/breeds/"
const val RANDOM_DOGS = "image/random/{numberDogs}"
const val LIST_BREEDS = "list/all"

@JsonClass(generateAdapter = true)
interface DogService {
    @GET(RANDOM_DOGS)
    suspend fun getDogs(
        @Path("numberDogs") numberDogs: String
    ): ResponseDog
    @GET(LIST_BREEDS)
    suspend fun getAllBreeds(): ResponseListBreeds
}

object RetrofitInstance {
    private val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val Service: DogService by lazy {
        retrofit.create(DogService::class.java)
    }
}
