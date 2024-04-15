package com.guiller.proyecto.ui.cuenta.detalleCuenta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.repository.detalleactivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class detalleActivityViewModel @Inject constructor(private val repositorio: detalleactivityRepository):ViewModel() {

    fun eleminarpref(){
        repositorio.eleminarPref()
    }

    fun eleminarDTB() {
        viewModelScope.launch {
            repositorio.eliminarDTB()
        }

    }


}




class detalleActivityViewModelFactory(private val respositorio:detalleactivityRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return detalleActivityViewModel(respositorio) as T
    }
}