package com.guiller.proyecto.ui.cuenta.detalleCuenta.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.DatosReView.detalleCuentaAdapter
import com.guiller.proyecto.datos.repository.detalleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(private val repositorio: detalleRepository) : ViewModel() {

    private val _detaCuenta = MutableLiveData<detalleCuentaAdapter>()
    val detaCuenta: LiveData<detalleCuentaAdapter> get() = _detaCuenta


    init {

        viewModelScope.launch {
            if (repositorio.retonarCuenta() != null) {
                _detaCuenta.value = repositorio.retonarCuenta()
            }
        }
    }


}

class DetalleViewModelFactory(private val repositorio: detalleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetalleViewModel(repositorio) as T
    }
}