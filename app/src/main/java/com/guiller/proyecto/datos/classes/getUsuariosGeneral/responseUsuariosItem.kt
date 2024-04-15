package com.guiller.proyecto.datos.classes.getUsuariosGeneral

import com.google.gson.annotations.SerializedName

data class responseUsuariosItem(
    @SerializedName ("apellido") val apellido: String,
    @SerializedName ("direccion") var direccion: String,
    @SerializedName ("edad") val edad: Int,
    @SerializedName ("id") val id: Int,
    @SerializedName ("nombre") val nombre: String
)