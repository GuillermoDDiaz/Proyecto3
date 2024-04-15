package com.guiller.proyecto.ui.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.DatosReView.usuariosGeneralAdapter
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem
import com.guiller.proyecto.datos.mapper.datosEntidad
import com.guiller.proyecto.datos.repository.mainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repositorio: mainRepository) : ViewModel() {


    private val _usuarios = MutableLiveData<usuariosGeneralAdapter>()
    val usuarios: LiveData<usuariosGeneralAdapter> get() = _usuarios

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> get() = _nombre



    private val _exito = MutableLiveData(false)
    val exito:LiveData<Boolean>get() = _exito


    init {
        viewModelScope.launch{
            repositorio.eleminarDTB()
        }

        viewModelScope.launch {
            val body = repositorio.getUsuarios()
            _usuarios.value = usuariosGeneralAdapter(body) { selectUsuario(it) }
        }

    }

    private fun selectUsuario(usuario:responseUsuariosItem) {

        val entidadUsuario = usuario.datosEntidad()
//        val entidadUsuario = entidadUsuario(
//            usuario.id,
//            usuario.nombre,
//            usuario.apellido,
//            usuario.direccion,
//            usuario.edad
//        )
        viewModelScope.launch{
            _nombre.value = usuario.nombre
            _exito.value = repositorio.ingresarUsuario(entidadUsuario)
        }

    }

    override fun onCleared() {
        super.onCleared()

        viewModelScope.launch{
            repositorio.eleminarDTB()
        }
    }
}

class MainViewModelFactory(private val repositorio:mainRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repositorio) as T
    }

}

