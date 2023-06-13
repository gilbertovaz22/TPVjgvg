package com.jgvg.tpvjgvg.views.Productos

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgvg.tpvjgvg.modelos.Producto
import com.jgvg.tpvjgvg.network.response.ProductoResponse
import com.jgvg.tpvjgvg.network.response.RetrofitClient
import com.jgvg.tpvjgvg.utils.Permisos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class ProductosViewModel : ViewModel() {

    private var _listaProductos = MutableLiveData<MutableList<Producto>>()

    val listaProductos: LiveData<MutableList<Producto>> get() = _listaProductos
//// proveedores

    private var _listaNomProveedores = MutableLiveData<ArrayList<String>>()

    val listaNomProveedores: LiveData<ArrayList<String>> get() = _listaNomProveedores

    private var _mensaje = MutableLiveData<String>()
    val mensaje: LiveData<String> get() = _mensaje

    private lateinit var response: Response<ProductoResponse>
//////////////////////////////////// Metodos ////////////////////
    fun getProductos (){
        viewModelScope.launch (Dispatchers.IO ){
            val response = RetrofitClient.webServices.getProductos()
            withContext(Dispatchers.Main){
                if (response.body()!!.codigo == "200"){
                    _listaProductos.value = response.body()!!.resultado
                } else{
                    _mensaje.value = response.body()!!.mensaje
                }
            }
        }
    }

    fun getNomProveedores(){
        viewModelScope.launch ( Dispatchers.IO ){
            val response = RetrofitClient.webServices.getProveedores()
            withContext(Dispatchers.Main){
                if (response.body()!!.codigo == "200"){
                    val aux = arrayListOf<String>()
                    response.body()!!.resultado.forEach{
                        aux.add(it.nomProveedor)
                    }
                    _listaNomProveedores.value = aux
                } else{
                    _mensaje.value = response.body()!!.mensaje
                }
            }
        }
    }

    fun filtrarListaProductos(producto: String){
        val listaFiltrada: MutableList<Producto> = mutableListOf()
        for (prod in _listaProductos.value!!){
            if (prod.codProducto.contains(producto) || prod.nomProducto.contains(producto)){
                listaFiltrada.add(prod)
            }
        }
        _listaProductos.value = listaFiltrada
    }
    fun validarCampos(
        accion: String,
        codigo: String,
        nomProducto: String,
        descripcion: String,
        nomProveedor: String,
        precio: String,
        almacen: String,

    ){
        if (
            codigo.isNullOrEmpty()
            || nomProducto.isNullOrEmpty()
            ||descripcion.isNullOrEmpty()
            ||nomProveedor.isNullOrEmpty()
            ||precio.isNullOrEmpty()
            ||almacen.isNullOrEmpty()
        ){
            _mensaje.value="Llena todos los Campos"
        }else{
            val prod = Producto(
                almacen.toInt(),
                codigo,
                descripcion,
                nomProducto,
                nomProveedor,
                precio.toDouble()
            )

            productoAddUpdate(accion, prod)
        }

    }

    private  fun productoAddUpdate(accion: String, prod: Producto){
       viewModelScope.launch(Dispatchers.IO ){
          if (accion == "add"){
              response = RetrofitClient.webServices.addProducto(prod)


          }else{
              response = RetrofitClient.webServices.updateProducto(prod.codProducto, prod)
          }
           withContext(Dispatchers.Main){
               if (response.body()!!.codigo == "200") {
                   _mensaje.value = response.body()!!.mensaje
                    getProductos()
               }else{
                   _mensaje.value = response.body()!!.mensaje
               }

              }
           }

       }
    fun  checkCamaraPermiso(activity: Activity):Boolean{
     val isPermiso =   Permisos().checkCamaraPermiso(activity)
        return isPermiso
    }
}









