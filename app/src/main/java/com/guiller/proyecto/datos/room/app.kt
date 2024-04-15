package com.guiller.proyecto.datos.room

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var storage: SharedPreferences
        private var basedatos: BaseDatos? = null
        fun getPref(): SharedPreferences = storage
        fun getDB(): BaseDatos = basedatos!!
    }


    override fun onCreate() {
        super.onCreate()
        //Desactivar el modo noche
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val SHAREDNAME = "MyData"
        storage = this.getSharedPreferences(SHAREDNAME, 0)


        if (basedatos == null) {
            basedatos = Room.databaseBuilder(
                this,
                BaseDatos::class.java,
                "proyectos"
            ).build()
        }

        CoroutineScope(Dispatchers.IO).launch {
            basedatos!!.peticiones().eleminarDTBusuario()
            basedatos!!.peticiones().eleminarDTBcuenta()
        }



    }




}