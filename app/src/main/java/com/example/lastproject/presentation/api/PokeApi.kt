package com.example.lastproject.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PokeApi {
    @GET("pokemon")
    fun getPokemonList():Call<PokemonResponse>

    @GET("pokemon/{{id}}")
    fun getPokemonDetail(@Path("id") id:String):Call<PokemonResponse>

    }

