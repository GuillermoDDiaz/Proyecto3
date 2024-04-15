package com.guiller.proyecto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.repository.datoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Menu_NavegacionViewModel @Inject constructor (private val repositorio: datoRepository) : ViewModel() {

    private val _nombres = MutableLiveData<String>()
    val nombres: LiveData<String> get() = _nombres

//iniciar viewModel
    init {
        viewModelScope.launch {
            _nombres.value = repositorio.getNombres()
        }
    }

//Eliminar base de datos
   fun eliminarDTB(){
        viewModelScope.launch {
            repositorio.eleminarDTBS()
        }
   }

    override fun onCleared() {

        super.onCleared()
        viewModelScope.launch {
            repositorio.eleminarDTBS()
        }

    }

}


