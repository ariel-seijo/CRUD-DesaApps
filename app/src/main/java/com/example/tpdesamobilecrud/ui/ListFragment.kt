package com.example.tpdesamobilecrud.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpdesamobilecrud.R
import com.example.tpdesamobilecrud.model.ClienteExample
import com.example.tpdesamobilecrud.databinding.FragmentListBinding
import com.example.tpdesamobilecrud.viewmodel.ClientViewModel

class ListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentListBinding

    private val clientViewModel by viewModels<ClientViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = ClienteAdapter()
        binding.recyclerViewCliente.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCliente.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recyclerViewCliente.addItemDecoration(divider)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)



        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


            clientViewModel.readAllData.observe(viewLifecycleOwner) { clientList ->
            adapter.setList(clients = clientList)
        }



        return binding.root
    }

    private fun deleteAll() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Eliminar Todos?")
        dialog.setMessage("¿Esta seguro que desea eliminar a todos los usuarios?")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            clientViewModel.deleteAllClients()
        }

        dialog.create().show()
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteAll()
                true
            }

            else -> {
                false
            }
        }
    }


}