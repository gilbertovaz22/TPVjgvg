package com.jgvg.tpvjgvg.network.response

import com.jgvg.tpvjgvg.modelos.Producto
import com.jgvg.tpvjgvg.modelos.Proveedor

data class ProveedorResponse(

    val codigo: String,
    val mensaje: String,
    val resultado: MutableList<Proveedor>
)
