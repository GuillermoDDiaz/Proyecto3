package com.guiller.proyecto.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.ActivityMenuNavegacionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Menu_Navegacion : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuNavegacionBinding
    private val viewModel: Menu_NavegacionViewModel by viewModels ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuNavegacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMenuNavegacion.toolbar)
        binding.appBarMenuNavegacion.toolbar.setBackgroundResource(R.drawable.ic_tooblarfondo)

        //Obtener nombres

        viewModel.nombres.observe(this, Observer {
            headerNombre(it)
        })


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu_navegacion)

        //Menu lateral de opciones
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Controlar boton hacia atras

        pressBack()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu__navegacion, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu_navegacion)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun headerNombre(nombre: String) {
        val viewH = binding.navView.getHeaderView(0)

        val txtHNombre: TextView = viewH.findViewById(R.id.txtHNombre)
        txtHNombre.text = nombre
    }


//Funciones con los items selecionados
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.salir -> salir()
            R.id.nav_home -> {
                binding.appBarMenuNavegacion.toolbar.setBackgroundResource(R.drawable.ic_tooblarfondo)
            }

            R.id.nav_slideshow -> {
                binding.appBarMenuNavegacion.toolbar.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.primary
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //detectar click hacia atras

    private fun pressBack() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                salir()
            }
        })

    }

    //Alerta de salida

    private fun salir() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Salir")
        builder.setMessage("Esta seguro que desea salir")
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            viewModel.eliminarDTB()
            finish()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.eliminarDTB()
    }


}