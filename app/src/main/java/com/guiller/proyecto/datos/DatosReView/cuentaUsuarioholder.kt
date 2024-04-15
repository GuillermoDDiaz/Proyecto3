package com.guiller.proyecto.datos.DatosReView


import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.RvCuentaUsuarioBinding
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuarioItem
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import java.text.DecimalFormat


class cuentaUsuarioholder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = RvCuentaUsuarioBinding.bind(view)


    @SuppressLint("SetTextI18n")
    fun render(cuenta: entidadCuentas,posicion:Int, pos:(Int)->Unit,fav:(entidadCuentas)->Unit,select:(Int)->Unit) {
        binding.txtNumbCuenta.text = cuenta.cuenta.toString()
        binding.txtTipoC.text = cuenta.tipoCuenta
        binding.txtSaldo.text = conversion( cuenta.saldo!!)
        binding.txtNombreComp.text = cuenta.nombres

        if (cuenta.favorita!!) {
            binding.icFav.setImageResource(R.drawable.ic_fav_selec)
        } else {
            binding.icFav.setImageResource(R.drawable.ic_fav)
        }
        binding.icFav.setOnClickListener {
            fav(cuenta)
            pos(posicion)
        }
        binding.LSelec.setOnClickListener{

            select(cuenta.id)

        }

    }
    private fun conversion(valor:Double):String{
        val df = DecimalFormat("#,###,###,###,###.##")
        return decimales( df.format(valor))
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