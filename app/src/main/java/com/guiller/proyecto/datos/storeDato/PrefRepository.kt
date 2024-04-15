package com.guiller.proyecto.datos.storeDato

import com.guiller.proyecto.datos.room.App
import javax.inject.Inject

class prefRepository @Inject constructor() {
    val storge = App.getPref()

    // apartado de cuenta
    var idCuenta: Int
        get() = storge.getInt("id_cuenta", -1)
        set(value) = storge.edit().putInt("id_cuenta", value).apply()





}