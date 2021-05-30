package com.example.lastproject.presentation
import com.example.lastproject.presentation.PokeApplication.Companion.context
import com.example.lastproject.presentation.api.PokeApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class Singleton {
    companion object{


        var cache = Cache(File(context?.getCacheDir(), "responses"), 10 * 1024 * 1024)// 10 MiB

        val okHttpClient : OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        val pokeApi: PokeApi = Retrofit.Builder()

            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .build().create(PokeApi::class.java)


    }
}

