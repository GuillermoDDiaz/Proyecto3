package com.guiller.proyecto.datos.DatosReView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.R
import com.guiller.proyecto.datos.classes.getCuentaUsuario.getDetalleCuenta.detalleCuenta

class detalleCuentaAdapter(private val detalleCuenta: detalleCuenta):
    RecyclerView.Adapter<detalleCuentaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detalleCuentaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return detalleCuentaHolder(layoutInflater.inflate(R.layout.rv_detalle_cuenta, parent, false))
    }

    override fun getItemCount(): Int = detalleCuenta.size

    override fun onBindViewHolder(holder: detalleCuentaHolder, position: Int) {
        holder.render(detalleCuenta[position])
    }
}