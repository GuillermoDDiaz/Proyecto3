package com.guiller.proyecto.datos.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object llamadaRetrofit {
     fun usuariosGeneral(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://172.16.25.103:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}