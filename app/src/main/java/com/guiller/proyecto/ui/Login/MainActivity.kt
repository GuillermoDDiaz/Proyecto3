package com.guiller.proyecto.ui.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.guiller.proyecto.databinding.ActivityMain2Binding
import com.guiller.proyecto.datos.repository.mainRepository
import com.guiller.proyecto.ui.Menu_Navegacion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        viewModel.usuarios.observe(this, Observer {
            binding.rvUsuarios.adapter = it
        })

        viewModel.exito.observe(this, Observer {
            if (it)
                iniciar()
        })



    }



    private fun iniciar() {

            val intent = Intent(this, Menu_Navegacion::class.java)
            startActivity(intent)

    }


}