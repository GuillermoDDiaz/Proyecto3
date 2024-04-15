package com.guiller.proyecto.ui.cuenta

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.FragmentHomeBinding
import com.guiller.proyecto.datos.repository.datoRepository
import com.guiller.proyecto.ui.cuenta.detalleCuenta.detalleActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class cuentaFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val nuevaActividad = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
       if(it.resultCode == RESULT_CANCELED)
       {
           activity?.finish()
       }
    }
    private val homeViewModel: HomeViewModel by viewModels ()

    private var isSelect = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCuenta.layoutManager = LinearLayoutManager(context)

        homeViewModel.cuenta.observe(viewLifecycleOwner) {
            binding.rvCuenta.adapter = it
        }
        btnCambio(isSelect)
        listener()
        var nombreC = " "
        homeViewModel.nombre.observe(viewLifecycleOwner) {
            nombreC = it
        }
        var cuenta = ""
        homeViewModel.numCuenta.observe(viewLifecycleOwner) {
            cuenta = it
        }


        homeViewModel.bool.observe(viewLifecycleOwner) {
            if(it){
                    homeViewModel.cambioApp()
                    val intent = Intent(context, detalleActivity::class.java)
                    intent.putExtra("nombres", nombreC)
                    intent.putExtra("cuenta", cuenta)
                    nuevaActividad.launch(intent)
            }
        }

    }


    private fun listener() {
        binding.btnTodo.setOnClickListener {
            if (isSelect == false) {

                isSelect = !isSelect
                btnCambio(isSelect)
                homeViewModel.casoFavorita(0)
            }
        }
        binding.btnFav.setOnClickListener {
            if (isSelect == true) {

                isSelect = !isSelect
                btnCambio(isSelect)
                homeViewModel.casoFavorita(1)
            }


        }


    }


    private fun btnCambio(it: Boolean) {
        val colorNoSelect = ResourcesCompat.getColor(resources, R.color.Blanco, null)
        val colorSelect = ResourcesCompat.getColor(resources, R.color.botones, null)
        val colorTextNoSelect = ResourcesCompat.getColor(resources, R.color.texto_detalle, null)
        val colorTextSelect = ResourcesCompat.getColor(resources, R.color.primary, null)

        if (it) {

            binding.btnTodo.setTextColor(colorTextSelect)
            binding.btnFav.setTextColor(colorTextNoSelect)
            binding.vTodo.setBackgroundColor(colorSelect)
            binding.vFav.setBackgroundColor(colorNoSelect)

        } else {
            binding.btnTodo.setTextColor(colorTextNoSelect)
            binding.btnFav.setTextColor(colorTextSelect)
            binding.vTodo.setBackgroundColor(colorNoSelect)
            binding.vFav.setBackgroundColor(colorSelect)
        }

    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.ic_tooblarfondo))
    }


}