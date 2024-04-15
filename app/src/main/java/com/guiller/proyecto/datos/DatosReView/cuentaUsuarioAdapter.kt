package com.guiller.proyecto.datos.DatosReView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.R
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuarioItem
import com.guiller.proyecto.datos.room.entidades.entidadCuentas

class cuentaUsuarioAdapter(var cuenta:List<entidadCuentas>,val pos:(Int)->Unit,val fav:(entidadCuentas)->Unit,val select:(Int)->Unit): RecyclerView.Adapter<cuentaUsuarioholder>() {

    fun updateList(newCuenta:List<entidadCuentas>)
    {
        val diffUtilCuenta = diffUtilCuenta(cuenta,newCuenta)
        val result = DiffUtil.calculateDiff(diffUtilCuenta)

        cuenta = newCuenta
        result.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cuentaUsuarioholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cuentaUsuarioholder(layoutInflater.inflate(R.layout.rv_cuenta_usuario, parent, false))
    }

    override fun getItemCount(): Int = cuenta.size  ///Tamano de la lista para saber cuantos van a cargar


    override fun onBindViewHolder(holder: cuentaUsuarioholder, position: Int) { //Pasa por cada uno de los items y devuelve la posicion
        val item =cuenta[position]
        holder.render(item,position,pos,fav,select)

    }
}