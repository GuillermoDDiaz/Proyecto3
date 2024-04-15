package com.guiller.proyecto.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.guiller.proyecto.databinding.DialogBinding

class dialogBP(private val titulo:String,private val mensaje:String):DialogFragment() {

    private lateinit var binding:DialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

         binding = DialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.titulo.text = titulo
        binding.mensaje.text = mensaje

        val dialogo = builder.create()
        return dialogo
    }
}