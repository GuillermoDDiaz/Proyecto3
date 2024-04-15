package com.guiller.proyecto.datos.classes.postTransaccion

import com.google.gson.annotations.SerializedName

data class TipoTransaccion(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String
)