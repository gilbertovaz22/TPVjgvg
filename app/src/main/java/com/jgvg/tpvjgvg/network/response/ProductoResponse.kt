package com.jgvg.tpvjgvg.network.response

import com.jgvg.tpvjgvg.modelos.Producto

data class ProductoResponse(

    val codigo: String,
    val mensaje: String,
    val resultado: MutableList<Producto>



)
