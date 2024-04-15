package com.guiller.proyecto.datos.DatosReView

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.RvDetalleCuentaBinding
import com.guiller.proyecto.datos.classes.getCuentaUsuario.getDetalleCuenta.detalleCuenta
import com.guiller.proyecto.datos.classes.getCuentaUsuario.getDetalleCuenta.detalleCuentaItem
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale

class detalleCuentaHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = RvDetalleCuentaBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(detalleCuenta: detalleCuentaItem) {



        binding.txtCantidad.text = conversion(detalleCuenta.monto)
        binding.txtPagp.text = detalleCuenta.descripcion

        when (detalleCuenta.tipoTransaccion.id) {
             2 -> {
                val colorCre = Color.parseColor("#36B640")
                binding.txtCantidad.setTextColor(colorCre)
                binding.txtMoneda.setTextColor(colorCre)
                 binding.txtCreDeb.text = "Crédito"
                 binding.txtDetallePagp.text = detalleCuenta.fecha + "-NOTA CRÉDITO (NC)"

             }
            1 ->{
                val colorDeb = Color.parseColor("#FB1707")
                binding.txtCantidad.setTextColor(colorDeb)
                binding.txtMoneda.setTextColor(colorDeb)
                binding.txtCreDeb.text = "Débito"
                binding.txtDetallePagp.text = detalleCuenta.fecha + "-NOTA DÉBITO (ND)"

            }
        }

    }

    private fun conversion(valor:Double):String{

        val df = DecimalFormat("#,###,###,###,###.##")
        val valor = df.format(valor)

        return decimales(valor)
    }

    private fun decimales(valor: String): String {

       val separado = valor.split('.')

       return when(separado.size)
        {
            2 -> {
                if(separado[1].length == 1){
                    separado[0]+ "."+separado[1]+"0"
                }else if (separado[1].length == 2) {
                    separado[0] +"."+separado[1]
                }else{
                    valor
                }
            }
            else ->{
                valor +".00"
            }
        }
    }

}