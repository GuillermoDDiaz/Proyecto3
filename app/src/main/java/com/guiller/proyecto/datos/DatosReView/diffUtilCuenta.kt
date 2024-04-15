package com.guiller.proyecto.datos.DatosReView

import androidx.recyclerview.widget.DiffUtil
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.room.entidades.entidadCuentas

class diffUtilCuenta(private val old:List<entidadCuentas> , private val new:List<entidadCuentas>):DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].id == new[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return old[oldItemPosition] == new[newItemPosition]
    }


}