package com.guiller.proyecto.ui.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.DatosReView.usuariosGeneralAdapter
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem
import com.guiller.proyecto.datos.repository.mainRepository
import com.guiller.proyecto.datos.storeDato.prefRepository
import com.guiller.proyecto.datos.retrofit.llamadaRetrofit
import com.guiller.proyecto.datos.retrofit.retrofit
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
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

            val retrofitR = llamadaRetrofit.usuariosGeneral()
            val respuesta = retrofitR.create(retrofit::class.java).getUsuariosGeneral()
            if (respuesta.isSuccessful) {
                _usuarios.value = usuariosGeneralAdapter(respuesta.body()!!) { selectUsuario(it) }
            }

        }

    }

    private fun selectUsuario(usuario:responseUsuariosItem) {

        val entidadUsuario = entidadUsuario(
            usuario.id,
            usuario.nombre,
            usuario.apellido,
            usuario.direccion,
            usuario.edad
        )
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

