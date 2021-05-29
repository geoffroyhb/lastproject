package com.example.lastproject.presentation.api

import com.example.lastproject.presentation.list.Pokemon

data class PokemonListResponse(
    val count:Int,
     val next:String,
    val results: List<Pokemon>



)