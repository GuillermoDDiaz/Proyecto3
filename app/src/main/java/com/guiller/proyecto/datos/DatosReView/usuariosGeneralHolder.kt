package com.guiller.proyecto.datos.DatosReView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.databinding.RvUsuariosGeneralBinding
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem

class usuariosGeneralHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = RvUsuariosGeneralBinding.bind(view)



    @SuppressLint("SetTextI18n")
    fun render(usuarios: responseUsuariosItem,  select:(responseUsuariosItem)->Unit) {
        binding.txtNombres.text = "${usuarios.nombre}  ${usuarios.apellido}"
        binding.txtDireccion.text = usuarios.direccion
        binding.txtEdad.text = usuarios.edad.toString()

        binding.btnSelect.setOnClickListener { select(usuarios) }
    }

}