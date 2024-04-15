package com.guiller.proyecto.ui.cuenta.detalleCuenta

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.guiller.proyecto.R
import com.guiller.proyecto.databinding.ActivityDetalleBinding
import com.guiller.proyecto.datos.repository.detalleactivityRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class detalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding
    private val viewModel: detalleActivityViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_detalle)
        binding.txtNombres.text = intent.getStringExtra("nombres")
        binding.txtCuen.text = intent.getStringExtra("cuenta")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalle de cuenta"

pressBack()
    }
    private fun pressBack() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(RESULT_OK)
                viewModel.eleminarpref()
                finish()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu__navegacion, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.salir -> {
                viewModel.eleminarDTB()
                salir()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun salir() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Salir")
        builder.setMessage("Esta seguro que desea salir")
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            setResult(RESULT_CANCELED)
            viewModel.eleminarpref()
            finish()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }


}