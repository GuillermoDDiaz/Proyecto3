package com.guiller.proyecto.ui.cuenta.detalleCuenta.nuevaTransac

import android.app.Dialog
import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.FragmentNuevaTransBinding
import com.guiller.proyecto.datos.repository.nuevaTrasnRepository
import com.guiller.proyecto.ui.dialog.dialogBP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class nuevaTransFragment : Fragment() {



    private lateinit var _binding: FragmentNuevaTransBinding
    private val binding get() = _binding
    private var paso = false
    private val viewModel: NuevaTransViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Nueva transacción"
        _binding = FragmentNuevaTransBinding.inflate(inflater, container, false)
        return binding.root
    }

                                                                                                                                                                                                     @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.hacia_atras)
            }

        })

        funcionesObserve()


    }
                                                                                                                                                                                                         @RequiresApi(Build.VERSION_CODES.O)
    private fun funcionesObserve() {
        viewModel.exito.observe(viewLifecycleOwner) {
            if(paso)
                exito(it)
            else
                paso = true
        }
        binding.btnGuardar.setOnClickListener {
            val monto = binding.txtMonto.text.toString()

            if (monto != "") {
                viewModel.datos(
                    binding.txtMonto.text.toString(),
                    "Pruebas Proyectos",
                    binding.ckCreDeb.isChecked
                )
                viewModel.guardar()
            } else {

                Toast.makeText(context, "rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun exito(it: Boolean): Boolean {
        return when (it) {
            true -> {
                mensaje("BP en linea", "Transacción guardada con exito")
                limpiar()
                true
            }

            false -> {
                mensaje("BP en linea", "Transacción guardada sin exito intente nuevamente ")
                false
            }

        }
    }

    private fun limpiar() {
        binding.txtMonto.text = null
    }

    private fun mensaje(titulo: String, mensaje: String) {
        val dialog = dialogBP(titulo,mensaje)
        dialog.show(parentFragmentManager,"")
    }
}