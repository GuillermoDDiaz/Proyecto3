package com.guiller.proyecto.datos.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object putUser {
    fun putUsuario():Retrofit{

            return Retrofit.Builder()
                .baseUrl("http://172.16.25.103:8080/api/usuarios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}