package com.jgvg.tpvjgvg.network.response

import com.jgvg.tpvjgvg.modelos.Producto
import com.jgvg.tpvjgvg.modelos.Proveedor
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {

    //proveedor

    @GET("/proveedores")
    suspend fun getProveedores(): retrofit2.Response<ProveedorResponse>

    @POST ("/proveedores/add")
    suspend fun addProveedor(
        @Body prov: Proveedor
    ): retrofit2.Response<ProveedorResponse>

    @PUT("/proveedores/update/{nomProveedor}")
    suspend fun updateProveedor(
        @Path("nomProveedor") nomProveedor: String,
        @Body prov: Proveedor

    ): retrofit2.Response<ProveedorResponse>

    // end proveedores


    // productos
    @GET("/productos")
    suspend fun getProductos(): retrofit2.Response<ProductoResponse>

    @GET ("/productos/{codProducto}")
    suspend fun getProducto(
        @Path("codProducto") codProducto: String
            ): retrofit2.Response<ProductoResponse>

    @POST("/productos/add")
    suspend fun addProducto(
          @Body prov: Producto

    ): retrofit2.Response<ProductoResponse>

    @PUT("/productos/update/{codProducto}")
    suspend fun updateProducto(
        @Path("codProducto") codProducto: String,
        @Body prov: Producto

    ): retrofit2.Response<ProductoResponse>
// end ventas

    /// ventas

    @GET("/ventas/periodo")
    suspend fun getVentasPreriodo(
        @Query ("fechaInicio") fechaInicio: String,
        @Query ("fechaFinal") fechaFinal: String,

    ): retrofit2.Response<VentaResponse>

    @POST("/ventas/add")
    suspend fun addVentas(
        @Body datosSend: VentaResponse

    ): retrofit2.Response<VentaResponse>

    // end



}