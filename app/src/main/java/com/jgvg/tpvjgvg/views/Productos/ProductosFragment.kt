package com.jgvg.tpvjgvg.views.Productos

import android.app.AlertDialog
import android.content.AbstractThreadedSyncAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

    private lateinit var etCodigo: EditText

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
        binding.ibtnAdd.setOnClickListener{
            alertDialogAddUpdate("add")
        }
    }
    private  fun  setupRecyclerView(){
     binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductoAdapter(arrayListOf(), this)
        binding.rvProductos.adapter = adapter
    }

    private  fun alertDialogAddUpdate(accion: String){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater

        val vista = inflater.inflate(R.layout.alert_dialog_producto, null)
        builder.setView(vista)

        if (accion == "add"){
            builder.setTitle("Agregar Producto")
        }

        builder.setCancelable(false)

        etCodigo = vista.findViewById(R.id.etCodigo)

        val ibtnEscaner = vista.findViewById(R.id.ibtnEscaner) as ImageButton
        val etNomProducto = vista.findViewById<EditText>(R.id.etNomProducto) as EditText
        val etDescripcion = vista.findViewById<EditText>(R.id.etDescripcion) as EditText
        val spiProveedor = vista.findViewById<EditText>(R.id.spiProveedor)as Spinner
        val tvNomProveedor = vista.findViewById<EditText>(R.id.tvNomProveedor) as TextView
        val etPrecio = vista.findViewById<EditText>(R.id.etPrecio)as EditText
        val etAlmacen = vista.findViewById<EditText>(R.id.etAlmacen)as EditText

        viewModel.getNomProveedores()
        viewModel.listaNomProveedores.observe(requireActivity()){
            spiProveedor.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it

            )
        }




        builder.setPositiveButton("Aceptar"){ _, _ ->
            viewModel.validarCampos(
                accion,
                etCodigo.text.toString().trim(),
                etNomProducto.text.toString().trim(),
                etDescripcion.text.toString().trim(),
                spiProveedor.selectedItem.toString(),
                etPrecio.text.toString().trim(),
                etAlmacen.text.toString().trim()


            )
            adapter.notifyDataSetChanged()

        }


        builder.setNegativeButton("Cancelar"){ _, _ ->

        }

        builder.show()

    }

    override fun editarProducto(prod: Producto) {
        TODO("Not yet implemented")
    }
}