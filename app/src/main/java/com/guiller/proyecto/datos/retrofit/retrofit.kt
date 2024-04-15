package com.guiller.proyecto.datos.retrofit

import com.guiller.proyecto.datos.classes.getCuentaUsuario.cUsuario
import com.guiller.proyecto.datos.classes.getCuentaUsuario.getDetalleCuenta.detalleCuenta
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuarios
import com.guiller.proyecto.datos.classes.getUsuariosGeneral.responseUsuariosItem
import com.guiller.proyecto.datos.classes.postTransaccion.transaccionDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface retrofit {

    @GET("usuarios/")
    suspend fun getUsuariosGeneral(): Response<responseUsuarios>

    @GET("usuarios/por-nombre/{nombre}")
    suspend fun getUsuarioUnico(@Path("nombre") nombre: String): Response<responseUsuarios?>

    @GET("cuentas-propias/por-usuario/{id}")
    suspend fun getCuenta(@Path("id") id: Int): Response<cUsuario?>

    @GET("transacciones/por-cuenta/{id}")
    suspend fun getDetalle(@Path("id") id: Int): Response<detalleCuenta?>


    @POST("transacciones/")
    suspend fun postTransaccion(@Body transaccionDto: transaccionDto): transaccionDto?


    @PUT("{id}")
    suspend fun putUsuario(
        @Path("id") id: Int,
        @Body usuario: responseUsuariosItem
    ): responseUsuariosItem?
}