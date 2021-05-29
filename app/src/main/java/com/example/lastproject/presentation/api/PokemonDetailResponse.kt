package com.example.lastproject.presentation.api

class PokemonDetailResponse (
    val name:String,
    val types:List<PokemonSlot>
)

data class PokemonSlot(

    val slot:Int,
            val types:PokemonType
)

data class PokemonType(
    val name:String,
    val Url:String
)