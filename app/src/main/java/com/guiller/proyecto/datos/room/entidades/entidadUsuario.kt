package com.guiller.proyecto.datos.room.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class entidadUsuario (
    @PrimaryKey val id:Int,
    @ColumnInfo("nombre")val nombre:String?,
    @ColumnInfo("apellido")val apellido:String?,
    @ColumnInfo("direccion") val direccion:String?,
    @ColumnInfo("edad") val edad:Int?
)
