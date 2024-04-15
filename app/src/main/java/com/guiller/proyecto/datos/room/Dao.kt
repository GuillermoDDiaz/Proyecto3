package com.guiller.proyecto.datos.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import com.guiller.proyecto.datos.room.entidades.entidadUsuario

@Dao
interface Dao {

    @Insert
    suspend fun insertarUsario(usuario:entidadUsuario)

@Query("UPDATE entidadUsuario SET direccion =:direc WHERE id =:id")
suspend fun actualizarUsuario(direc:String, id:Int)

    @Query("SELECT * FROM entidadUsuario")
    suspend fun obtenerUsuario():entidadUsuario

    @Query("SELECT id FROM entidadUsuario")
    suspend fun usuarioID():Int

    @Query("SELECT id,nombre,apellido FROM entidadUsuario")
    suspend fun obtenerUsuarioUnico():entidadUsuario

    @Query("DELETE FROM entidadUsuario")
    suspend fun eleminarDTBusuario()

    @Insert
    suspend fun insertarCuenta(entidadCuentas: entidadCuentas)

    @Query("DELETE FROM entidadCuentas")
    suspend fun eleminarDTBcuenta()

    @Query("SELECT * FROM entidadCuentas")
    suspend fun obtenerCuentas():List<entidadCuentas>

    @Query("SELECT id,nCuenta,tipoCuenta,idCuenta,nombreCuenta FROM entidadCuentas WHERE id =:id")
    suspend fun obtenerCuentaUnica(id:Int):entidadCuentas

    @Query("UPDATE entidadCuentas SET favorita = 1 WHERE id =:pos ")
    suspend fun favoritaCuentas(pos:Int)

    @Query("UPDATE entidadCuentas SET favorita = 0 WHERE id =:posi ")
    suspend fun noFavoritaCuentas(posi:Int)

    @Query("SELECT * FROM entidadCuentas WHERE favorita =:fav ")
    suspend fun cuentasFavoritas(fav:Int):List<entidadCuentas>

}