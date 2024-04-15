package com.guiller.proyecto.ui.cuenta.detalleCuenta.nuevaTransac

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guiller.proyecto.datos.repository.nuevaTrasnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NuevaTransViewModel @Inject constructor(private val repositorio:nuevaTrasnRepository) : ViewModel() {

    private lateinit var montoP:String
    private lateinit var desc:String
    private  var creDeb: Boolean = false

    private val _exito = MutableLiveData(false)
    val exito : LiveData<Boolean> get() = _exito




    fun datos(montos:String,descripccion:String,crebDeb:Boolean){
        montoP = montos
        desc= descripccion
        creDeb= crebDeb

    }

                                                                                                                                                                                                                              @RequiresApi(Build.VERSION_CODES.O)
    fun guardar(){
        transaccion()
    }
                                                                                                                                                                                                                                    @RequiresApi(Build.VERSION_CODES.O)
private fun transaccion(){
    repositorio.datos(montoP,desc,creDeb)
    viewModelScope.launch {
        repositorio.guardarDato()
        _exito.value = repositorio.exito
    }
}


}
class NuevaTransViewModelFactory(private val repositorio:nuevaTrasnRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NuevaTransViewModel(repositorio) as T
    }
}