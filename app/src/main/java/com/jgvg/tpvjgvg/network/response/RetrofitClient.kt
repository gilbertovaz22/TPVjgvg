package com.jgvg.tpvjgvg.network.response

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jgvg.tpvjgvg.utils.Constantes
import com.jgvg.tpvjgvg.utils.Constantes.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    val webServices: WebServices by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebServices :: class.java)
    }
}