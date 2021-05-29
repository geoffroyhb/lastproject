package com.example.lastproject.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastproject.R
import com.example.lastproject.presentation.Singleton
import com.example.lastproject.presentation.Singleton.Companion.pokeApi
import com.example.lastproject.presentation.api.PokeApi
import com.example.lastproject.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = PokemonAdapter(listOf(), ::onClickPokemon )





    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            adapter = this@PokemonListFragment.adapter
        }







            Singleton.pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
                override fun onFailure(call: Call<PokemonListResponse>,
                                       t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                   if (response.isSuccessful && response.body() != null) {
                       val pokemonResponse: PokemonListResponse? = response.body()!!

                           adapter.updateList(pokemonResponse!!.results)
                       }
                       }




            })



    }
    private fun onClickPokemon(pokemon: Pokemon) {
        findNavController().navigate(R.id.navigateToPokemonDetailsFragment)

    }

}
