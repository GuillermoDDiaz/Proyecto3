package com.guiller.proyecto.datos.classes.postTransaccion

import com.google.gson.annotations.SerializedName

data class CuentasPropias(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("cuenta") val cuenta: Any?,
    @SerializedName("saldo") val saldo: Any?,
    @SerializedName("favorita") val favorita: Any?,
    @SerializedName("usuario") val usuario: Any?,
    @SerializedName("tipoCuenta") val tipoCuenta: Any?

)