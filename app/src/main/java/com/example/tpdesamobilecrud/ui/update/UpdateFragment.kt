package com.example.tpdesamobilecrud.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.tpdesamobilecrud.model.ClienteExample
import com.example.tpdesamobilecrud.R
import com.example.tpdesamobilecrud.databinding.FragmentUpdateBinding
import com.example.tpdesamobilecrud.model.Cliente
import com.example.tpdesamobilecrud.viewmodel.ClientViewModel

class UpdateFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentUpdateBinding
    private val clientViewModel by viewModels<ClientViewModel>()

    private var client: Cliente? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        client = arguments?.getSerializable("client") as Cliente // Bundle

        binding.inputNombre.setText(client?.let { it.name })
        binding.inputCelular.setText(client!!.phone)
        binding.inputEmail.setText(client!!.email)

        binding.btnUpdateCliente.setOnClickListener {
            validateFields(client!!)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun validateFields(client: Cliente) {
        val name = binding.inputNombre.text.toString()
        val phone = binding.inputCelular.text.toString()
        val email = binding.inputEmail.text.toString()

        if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {

            val client = client.copy(name = name, phone = phone, email = email)
            clientViewModel.updateClient(client = client)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Complete todos los campos!", Toast.LENGTH_SHORT).show()
        }



    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteClient()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun deleteClient() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Desea Eliminar?")
        dialog.setMessage("Esta seguro que desea eliminar ha ${client!!.name}")

        dialog.setNegativeButton(getString(R.string.no_option)) { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _, _ ->
            clientViewModel.deleteClient(client = client!!)
            Toast.makeText(requireContext(), "Cliente eliminado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        dialog.create().show()
    }


}