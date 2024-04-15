package com.guiller.proyecto.datos.classes.getCuentaUsuario

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class dataCuenta(
    val id_Cuenta: Int,
    val tipo: String,
    val num_cuenta: Int,
    val fav: Boolean,
    val saldo: Int,
    val nombres: String
)