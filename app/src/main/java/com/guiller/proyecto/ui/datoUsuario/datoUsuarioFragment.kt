package com.guiller.proyecto.ui.datoUsuario

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.FragmentDatoUsuariosBinding
import com.guiller.proyecto.datos.repository.posUsuarioRepository
import com.guiller.proyecto.ui.dialog.dialogBP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class datoUsuarioFragment : Fragment() {

    private var _binding: FragmentDatoUsuariosBinding? = null
    private var paso = false
    private val binding get() = _binding!!
    private val viewModel: SlideshowViewModel by viewModels ()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Establecer color de barra
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
            activity?.resources?.getDrawable(R.color.primary)
        )
        //ViewBinding
        _binding = FragmentDatoUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.back)
                }

            })


        //Funciones de viewModel
        observe()

        //Escucha de boton
        listeners()

    }

    private fun observe() {
        //Variable exito para saber si se actualizo el usarios
        viewModel.exito.observe(viewLifecycleOwner) {
            if (paso)
                exito(it)
            else
                paso = true
        }

        //Extraer nombre de usario
        viewModel.nombre.observe(viewLifecycleOwner) {
            binding.txtNombre.text = it
        }

        //Extraer apellido de usuario
        viewModel.apellido.observe(viewLifecycleOwner) {
            binding.txtApellido.text = it
        }
        //Extraer direccion de usuario
        viewModel.direccion.observe(viewLifecycleOwner) {
            binding.txtDireccione.hint = it
        }
    }


    private fun listeners() {
        binding.btnGuardar.setOnClickListener {

            val direcion = binding.txtDireccione.text ?: ""

            if (direcion.toString() != "") {
                viewModel.guardar(direcion.toString())
            } else {
                Toast.makeText(context, "Rellene el campo direccion", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun exito(it: Boolean) {

        if (it) {
            dialogBP("BP en linea", "Datos actualizados con éxito")
                .show(parentFragmentManager, "")

            limpiar()
        } else {
            dialogBP("BP en linea", "Datos actualizados sin éxito intente nuevamente")
                .show(parentFragmentManager, "")
        }

    }

    private fun limpiar() {
        binding.txtDireccione.text = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}