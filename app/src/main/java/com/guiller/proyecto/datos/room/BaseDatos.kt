package com.guiller.proyecto.datos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import com.guiller.proyecto.datos.room.entidades.entidadUsuario


@Database(entities = [entidadUsuario::class,entidadCuentas::class],version = 1)
abstract class BaseDatos : RoomDatabase() {
    abstract fun peticiones(): Dao

}