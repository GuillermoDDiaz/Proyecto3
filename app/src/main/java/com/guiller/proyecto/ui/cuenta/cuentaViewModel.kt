package com.guiller.proyecto.ui.cuenta

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.DatosReView.cuentaUsuarioAdapter
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuarioItem
import com.guiller.proyecto.datos.classes.getCuentaUsuario.dataCuenta
import com.guiller.proyecto.datos.repository.datoRepository
import com.guiller.proyecto.datos.room.entidades.entidadCuentas
import com.guiller.proyecto.datos.room.entidades.entidadUsuario
import com.guiller.proyecto.datos.storeDato.prefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositorio: datoRepository) : ViewModel() {

    private val pref = prefRepository()
    private var fav = true

    lateinit var cuentaAdap: cuentaUsuarioAdapter


    private val _bool = MutableLiveData(false)
    val bool: LiveData<Boolean> get() = _bool

    private val _cuenta = MutableLiveData<cuentaUsuarioAdapter>()
    val cuenta: LiveData<cuentaUsuarioAdapter> get() = _cuenta


    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> get() = _nombre

    private val _numCuenta = MutableLiveData<String>()
    val numCuenta: LiveData<String> get() = _numCuenta

    init {
        try {
            viewModelScope.launch {
                repositorio.datosCuentas()

                cuentaAdap =
                    cuentaUsuarioAdapter(
                        repositorio.retonarFavoritas(),
                        { cambio(it) },
                        { fav(it) },
                        { detalle(it) })


                _cuenta.value = cuentaAdap


            }
        } catch (e: Exception) {

        }

    }

    //Actualizar listas cuando cambia a favorita
    private fun cambio(it: Int) {
        viewModelScope.launch {
            if (fav) {
                cuentaAdap.updateList(repositorio.retonarFavoritas())
            } else {
                cuentaAdap.updateList(repositorio.retonarCuenta())
            }

            cuentaAdap.notifyItemChanged(it)


        }


    }

    //Cambiar el estado de favorita
    private fun fav(it: entidadCuentas) {
        viewModelScope.launch {
            if (it.favorita!!) {
                repositorio.noFavoritaCuenta(it.id)

            } else {
                repositorio.favoritaCuenta(it.id)

            }
        }

    }


    //Seleccion de cuenta para mostras su detalle
    private fun detalle(it: Int) {
        pref.idCuenta = it
        viewModelScope.launch {
            _nombre.value = repositorio.getNombres()
            _numCuenta.value = repositorio.getDatoCuenta()
            _bool.value = true
        }


    }




    fun casoFavorita(caso: Int) {
        cambioFavTodo(caso)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun cambioFavTodo(cambio: Int) {
        viewModelScope.launch {
            when (cambio) {
                1 -> {
                    cuentaAdap.updateList(repositorio.retonarFavoritas())
                    fav = true
                }

                0 -> {
                    cuentaAdap.updateList(repositorio.retonarCuenta())
                    fav = false
                }
            }

            cuentaAdap.notifyDataSetChanged()

        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            repositorio.eleminarDTBS()
        }
    }

    //Regresar a false el cambio de pantalla
    fun cambioApp() {
        _bool.value = false
    }


}


class HomeViewModelFactory(private val repositorio: datoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repositorio) as T
    }
}