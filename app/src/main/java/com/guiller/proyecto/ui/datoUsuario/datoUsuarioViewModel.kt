package com.guiller.proyecto.ui.datoUsuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuarios
import com.guiller.proyecto.datos.repository.posUsuarioRepository
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(private val repositorio: posUsuarioRepository) : ViewModel() {

    private lateinit var usuario: entidadUsuario

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> get() = _nombre

    private val _apellido = MutableLiveData<String>()
    val apellido: LiveData<String> get() = _apellido

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> get() = _direccion

    private val _exito = MutableLiveData<Boolean>(false)
    val exito: LiveData<Boolean> get() = _exito


    //Inicializar viewModel

    init {
        viewModelScope.launch {
           solicitarDatos()
        }
    }



    private suspend fun solicitarDatos(){
        usuario = repositorio.getUsario()
        extraerDatos(usuario)
    }
    //Extaer datos de usuario
    private fun extraerDatos(usuario: entidadUsuario) {
        _nombre.value = usuario.nombre!!
        _apellido.value = usuario.apellido!!
        _direccion.value = usuario.direccion!!

    }



    //Guardar el cambio
    fun guardar(direccion: String) {

        viewModelScope.launch {
            repositorio.guardar(direccion)
            _exito.value = repositorio.exito
            if (repositorio.exito)
                solicitarDatos()
        }


    }

}

