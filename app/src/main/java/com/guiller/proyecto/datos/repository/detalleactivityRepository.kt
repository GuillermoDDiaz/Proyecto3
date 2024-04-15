package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.storeDato.prefRepository
import javax.inject.Inject

class detalleactivityRepository @Inject constructor(val pref :prefRepository) {


    val DTB = App.getDB().peticiones()

    fun eleminarPref(){
        pref.idCuenta = -3
    }

   suspend fun eliminarDTB() {
        DTB.eleminarDTBcuenta()
        DTB.eleminarDTBusuario()
    }
}