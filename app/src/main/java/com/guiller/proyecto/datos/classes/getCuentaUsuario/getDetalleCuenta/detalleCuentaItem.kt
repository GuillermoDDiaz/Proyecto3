package com.guiller.proyecto.datos.classes.getCuentaUsuario.getDetalleCuenta

data class detalleCuentaItem(
    val cuentasPropias: CuentasPropias,
    val descripcion: String,
    val fecha: String,
    val id: Int,
    val monto: Double,
    val tipoTransaccion: TipoTransaccion
)