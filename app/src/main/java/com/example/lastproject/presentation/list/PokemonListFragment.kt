package com.example.lastproject.presentation.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastproject.R
import com.example.lastproject.presentation.Singleton.Companion.pokeApi
import com.example.lastproject.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = PokemonAdapter(listOf(), ::onClickPokemon )

    private val sharedPref:SharedPreferences?= activity?.getSharedPreferences(
        "app", Context.MODE_PRIVATE)







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


//        val list =getListFromCache()
//        if(list.isEmpty()){
        callApi()
//            }else{
//            showList(list)
//        }




    }

    private fun callApi() {
        pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(
                call: Call<PokemonListResponse>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val pokemonResponse: PokemonListResponse? = response.body()!!


                    showList(pokemonResponse!!.results)
                }
            }


        })
    }


    private fun showList(pokeList: List<Pokemon>) {
        adapter.updateList(pokeList)

    }

    private fun onClickPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailsFragment, bundleOf(
            "PokemonId" to (id+1)
        ))

    }

}

