package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuarios
import com.guiller.proyecto.datos.retrofit.llamadaRetrofit
import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
import javax.inject.Inject

class mainRepository @Inject constructor(){
    private val DB = App.getDB().peticiones()
    val retrofitR = llamadaRetrofit.usuariosGeneral()

    suspend fun ingresarUsuario(entidadUsuarios: entidadUsuario):Boolean{
        return try {
            DB.insertarUsario(entidadUsuarios)
            true
        }catch (e:Exception){
            false
        }
    }

    suspend fun eleminarDTB() {
        DB.eleminarDTBcuenta()
        DB.eleminarDTBusuario()
    }

   suspend fun getUsuarios(): responseUsuarios {
return solicitudUsuario()
    }

    private suspend fun solicitudUsuario(): responseUsuarios {

        val respuesta = retrofitR.create(retrofit::class.java).getUsuariosGeneral()
        return respuesta.body()!!

    }


}