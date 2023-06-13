package com.jgvg.tpvjgvg.network.response

import com.jgvg.tpvjgvg.modelos.DatosVentas
import com.jgvg.tpvjgvg.modelos.Producto

data class VentaResponse(

    val codigo: String,
    val mensaje: String,
    val resultado: List<DatosVentas>

)
