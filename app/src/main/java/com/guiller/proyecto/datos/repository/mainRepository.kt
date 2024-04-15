package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
import javax.inject.Inject

class mainRepository @Inject constructor(){
    private val DB = App.getDB().peticiones()

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


}