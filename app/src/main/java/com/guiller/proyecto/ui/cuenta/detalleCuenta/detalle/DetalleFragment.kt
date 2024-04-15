package com.guiller.proyecto.ui.cuenta.detalleCuenta.detalle

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.FragmentDetalleBinding
import com.guiller.proyecto.datos.repository.detalleRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleFragment : Fragment() {

    companion object {
        fun newInstance() = DetalleFragment()
    }

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetalleViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Detalle de cuenta"
        _binding = FragmentDetalleBinding.inflate(inflater, container,false)



    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detaCuenta.observe(viewLifecycleOwner){
            binding.rvDetalleCuenta.layoutManager = LinearLayoutManager(context)
            binding.rvDetalleCuenta.adapter = it
        }

        binding.btnNuevaTran.setOnClickListener {
           findNavController().navigate(R.id.action_detalleFragment2_to_nuevaTransFragment)
        }
    }


}