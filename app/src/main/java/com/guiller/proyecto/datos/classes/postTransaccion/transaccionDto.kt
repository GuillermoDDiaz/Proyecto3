package com.guiller.proyecto.datos.classes.postTransaccion

import com.google.gson.annotations.SerializedName

data class transaccionDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("monto") val monto: Double,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("tipoTransaccion") val tipoTransaccion: TipoTransaccion,
    @SerializedName("cuentasPropias") val cuentasPropias: CuentasPropias

)