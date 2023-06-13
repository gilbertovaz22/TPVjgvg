package com.jgvg.tpvjgvg.views.Productos

import android.app.AlertDialog
import android.content.AbstractThreadedSyncAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jgvg.tpvjgvg.R
import com.jgvg.tpvjgvg.databinding.FragmentProductosBinding
import com.jgvg.tpvjgvg.modelos.Producto

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ProductosFragment : Fragment(R.layout.fragment_productos), ProductoAdapter.OnItemClicked {

    private  lateinit var binding: FragmentProductosBinding

    private lateinit var adapter: ProductoAdapter
    private lateinit var viewModel: ProductosViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductosBinding.bind(view)

        viewModel = ViewModelProvider(this )[ProductosViewModel::class.java]

        setupRecyclerView()
        viewModel.getProductos()

        viewModel.listaProductos.observe(requireActivity()){
            adapter.listaProductos = it as ArrayList<Producto>
            adapter.notifyDataSetChanged()
        }
    }
    private  fun  setupRecyclerView(){
     binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductoAdapter(arrayListOf(), this)
        binding.rvProductos.adapter = adapter
    }

    private  fun alertDialogAddUpdate(){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater

        val vista = inflater.inflate(R.layout.alert_dialog_producto, null)
        builder.setView(vista)

        builder.setCancelable(false)

        val ibtnEscaner = vista.findViewById(R.id.ibtnEscaner) as ImageButton
        val etNomProducto = vista.findViewById<EditText>(R.id.etNomProducto) as EditText
        val etDescripcion = vista.findViewById<EditText>(R.id.etDescripcion) as EditText
        val spiProveedor = vista.findViewById<EditText>(R.id.spiProveedor)as Spinner
        val tvNomProveedor = vista.findViewById<EditText>(R.id.tvNomProveedor) as TextView
        val etPrecio = vista.findViewById<EditText>(R.id.etPrecio)as EditText
        val etAlmacen = vista.findViewById<EditText>(R.id.etAlmacen)as EditText




        builder.setPositiveButton("Aceptar"){ _, _ ->

        }


        builder.setNegativeButton("Cancelar"){ _, _ ->

        }

        builder.show()

    }

    override fun editarProducto(prod: Producto) {
        TODO("Not yet implemented")
    }
}