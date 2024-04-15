package com.guiller.proyecto.datos.repository

import com.guiller.proyecto.datos.DatosReView.detalleCuentaAdapter
import com.guiller.proyecto.datos.retrofit.llamadaRetrofit
import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.storeDato.prefRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class detalleRepository @Inject constructor(private val pref : prefRepository) {

    private val retrofitH = llamadaRetrofit.usuariosGeneral()


    suspend fun retonarCuenta(): detalleCuentaAdapter? {

        val id = pref.idCuenta
        var respuesta: detalleCuentaAdapter? = null
        try{
        coroutineScope {
            val llamada = retrofitH.create(retrofit::class.java).getDetalle(id)
            if (llamada.isSuccessful) {
                val body = llamada.body()
                if (body != null) {
                    respuesta = detalleCuentaAdapter(body)
                }
            }
        } }catch (e:Exception){

        }
        return respuesta

    }

}