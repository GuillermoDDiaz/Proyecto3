package com.guiller.proyecto.datos.DatosReView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.R
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuarios
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem

class usuariosGeneralAdapter (val usuarios: responseUsuarios, val select:(responseUsuariosItem)->Unit): RecyclerView.Adapter<usuariosGeneralHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usuariosGeneralHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return usuariosGeneralHolder(layoutInflater.inflate(R.layout.rv_usuarios_general, parent, false))
    }

    override fun getItemCount(): Int = usuarios.size  ///Tamano de la lista para saber cuantos van a cargar


    override fun onBindViewHolder(holder: usuariosGeneralHolder, position: Int) { //Pasa por cada uno de los items y devuelve la posicion
        val item =usuarios[position]
        holder.render(item, select)

    }
}