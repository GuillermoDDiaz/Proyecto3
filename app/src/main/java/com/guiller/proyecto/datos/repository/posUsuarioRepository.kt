package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem
import com.guiller.proyecto.datos.retrofit.putUser
import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class posUsuarioRepository @Inject constructor() {
    private val DTB = App.getDB().peticiones()
    private val retrofitPut = putUser.putUsuario()
    private lateinit var usuarios: entidadUsuario
    var exito = false


    suspend fun getUsario(): entidadUsuario {

        coroutineScope {
            usuarios = DTB.obtenerUsuario()
        }
        return usuarios
    }


    suspend fun guardar(direc: String) {
        val id = usuarios.id
        val user = responseUsuariosItem(
            usuarios.apellido!!,
            direc,
            usuarios.edad!!,
            usuarios.id,
            usuarios.nombre!!
        )

        exito = try {
           retrofitPut.create(retrofit::class.java).putUsuario(id, user)
            cambioUsuario(direc, usuarios.id)

            true

        } catch (e: Exception) {
            false
        }

    }

    private suspend fun cambioUsuario(direc: String, id: Int) {
        DTB.actualizarUsuario(direc, id)
    }


}