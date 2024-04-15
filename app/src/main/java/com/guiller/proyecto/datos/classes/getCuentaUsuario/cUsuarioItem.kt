package com.guiller.proyecto.datos.classes.getCuentaUsuario

data class cUsuarioItem(
    val cuenta: Int,
    var favorita: Boolean,
    val id: Int,
    val nombre: String,
    val saldo: Double,
    val tipoCuenta: TipoCuenta,
    val usuario: Usuario
)