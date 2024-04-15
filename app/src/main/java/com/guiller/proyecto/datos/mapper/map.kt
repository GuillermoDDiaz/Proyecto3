package com.guiller.proyecto.datos.mapper

import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuarioItem
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import com.guiller.proyecto.datos.room.entidades.entidadUsuario


fun responseUsuariosItem.datosEntidad(): entidadUsuario {
    return entidadUsuario(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        direccion = this.direccion,
        edad = this.edad
    )

}

fun cUsuario.cuentasEntidad(): List<entidadCuentas> {
    return this.map {
        entidadCuentas(
            id = it.id,
            nombres = it.usuario.nombre,
            cuenta = it.cuenta,
            nombreCuenta = it.nombre,
            saldo = it.saldo,
            idCuenta = it.tipoCuenta.id,
            tipoCuenta = it.tipoCuenta.nombre,
            favorita = it.favorita,

            )
    }
}