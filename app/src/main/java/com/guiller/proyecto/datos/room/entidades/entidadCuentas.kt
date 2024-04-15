package com.guiller.proyecto.datos.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class entidadCuentas(
    @PrimaryKey val id:Int,
    @ColumnInfo("nombres") val nombres:String?,
    @ColumnInfo("nCuenta") val cuenta:Int?,
    @ColumnInfo("nombreCuenta") val nombreCuenta:String?,
    @ColumnInfo("saldo") val saldo:Double?,
    @ColumnInfo("idCuenta") val idCuenta:Int?,
    @ColumnInfo("tipoCuenta") val tipoCuenta:String?,
    @ColumnInfo("favorita") val favorita:Boolean?,
)