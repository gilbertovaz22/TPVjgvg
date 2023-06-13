package com.jgvg.tpvjgvg.modelos

data class DatosVentas(
    val fechaVenta: String,
    val idVenta: String,
    val codProductos: String,
    val nomProveedor: List<ProdsVenta>,
    val total: Double

)
