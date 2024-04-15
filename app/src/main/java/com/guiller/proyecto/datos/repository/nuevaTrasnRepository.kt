package com.guiller.proyecto.datos.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.guiller.proyecto.datos.classes.postTransaccion.CuentasPropias
import com.guiller.proyecto.datos.classes.postTransaccion.TipoTransaccion
import com.guiller.proyecto.datos.classes.postTransaccion.transaccionDto
import com.guiller.proyecto.datos.retrofit.posRetrofit

import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.room.App
import com.guiller.proyecto.datos.storeDato.prefRepository
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class nuevaTrasnRepository @Inject constructor(val pref : prefRepository) {

    val retrofitH = posRetrofit.postTransac()
    private val DTB = App.getDB().peticiones()
    private lateinit var montoP: String
    private lateinit var desc: String
    private var idCD: Int = 0
    private lateinit var deDe: String

    var exito = false


    fun datos(montos: String, descripccion: String, crebDeb: Boolean) {
        montoP = montos
        desc = descripccion
        if (crebDeb) {
            idCD = 2
            deDe = "CREDITO"
        } else {
            idCD = 1
            deDe = "DEBITO"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun guardarDato() {
        coroutineScope {
            val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
            val fecha = LocalDateTime.now().format(formato)

            exito =  try {
                val datos = DTB.obtenerCuentaUnica(pref.idCuenta)


                val tipoTrans = TipoTransaccion(
                    idCD,
                    deDe
                )
                val idcp = datos.id
                val cuenta = datos.nombreCuenta

                val cuentaCP = CuentasPropias(
                    idcp,
                    cuenta!!,
                    null,
                    null,
                    null,
                    null,
                    null

                )
                val transaccion = transaccionDto(
                    null,
                    montoP.toDouble(),
                    desc,
                    fecha,
                    tipoTrans,
                    cuentaCP
                )


                retrofitH.create(retrofit::class.java).postTransaccion(transaccion)
                true
            } catch (e: Exception) {
                false
            }


        }


    }


}