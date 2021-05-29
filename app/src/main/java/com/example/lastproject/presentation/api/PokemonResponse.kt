package com.example.lastproject.presentation.api

import com.example.lastproject.presentation.list.Pokemon

data class PokemonResponse(
    val count:Int,
     val next:String,
    val results: List<Pokemon>



)