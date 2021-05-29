package com.example.lastproject.presentation

import com.example.lastproject.presentation.api.PokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Singleton {
    companion object{

             val pokeApi: PokeApi = Retrofit.Builder()

           .baseUrl("https://pokeapi.co/api/v2/")
            .build().create(PokeApi::class.java)

    }
}
