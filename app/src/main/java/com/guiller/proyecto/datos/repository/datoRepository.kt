package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.mapper.cuentasEntidad
import com.guiller.proyecto.datos.retrofit.llamadaRetrofit
import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import com.guiller.proyecto.datos.storeDato.prefRepository
import javax.inject.Inject


class datoRepository @Inject constructor(private val pref: prefRepository) {



    private val retrofitH = llamadaRetrofit.usuariosGeneral()
    private val DTB = App.getDB().peticiones()

    //Solicitando cuentas del usuario seleccionado
    suspend fun datosCuentas(): Boolean {
        return try {
            val cuenta = DTB.obtenerCuentas()
            if (cuenta.isEmpty()) {
                val llamada = retrofitH.create(retrofit::class.java).getCuenta(DTB.usuarioID())

                if (llamada.isSuccessful) {
                    val respuesta = llamada.body()
                    if (respuesta != null) {
                        insertarDTBcuenta(respuesta)
                    }
                }
            }
            true
        } catch (e: Exception) {

            false
        }


    }

    //Ingresando datos a Room para tenerlos a mano
    private suspend fun insertarDTBcuenta(re: cUsuario?) {
        if (re != null) {

            val cuentas = re.cuentasEntidad()
            DTB.insertarCuenta(cuentas)
        }
    }

    suspend fun retonarFavoritas() = DTB.cuentasFavoritas(1)


    //Retornando las cuentas almacenadas
    suspend fun retonarCuenta(): List<entidadCuentas> = DTB.obtenerCuentas()

    //Cambiando valor de favorita = true
    suspend fun favoritaCuenta(pos: Int) {
        DTB.favoritaCuentas(pos)
    }

    //Cambiando valor de favorita = false
    suspend fun noFavoritaCuenta(pos: Int) {
        DTB.noFavoritaCuentas(pos)
    }




    //Retorna nombre y apellido de usuario
    suspend fun getNombres(): String {
        val usuario = DTB.obtenerUsuarioUnico()
        val nombre: String = usuario.nombre + " " + usuario.apellido
        return nombre
    }





    //Datos de cuenta para mostar en panel
    suspend fun getDatoCuenta(): String {
        val datoCuenta = DTB.obtenerCuentaUnica(pref.idCuenta)

        val cuenta = datoCuenta.tipoCuenta + " " + datoCuenta.cuenta.toString()
        return cuenta

    }

    //eliminando base de datos
    suspend fun eleminarDTBS() {
        DTB.eleminarDTBusuario()
        DTB.eleminarDTBcuenta()
        pref.idCuenta = -1
    }

}