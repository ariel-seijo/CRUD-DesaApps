package com.example.tpdesamobilecrud.ui.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tpdesamobilecrud.model.ClienteExample
import com.example.tpdesamobilecrud.R
import com.example.tpdesamobilecrud.databinding.FragmentAddBinding
import com.example.tpdesamobilecrud.model.Cliente
import com.example.tpdesamobilecrud.viewmodel.ClientViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val clientViewModel by viewModels<ClientViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.btnAddCliente.setOnClickListener {
            val name = binding.inputNombre.text.toString()
            val phone = binding.inputCelular.text.toString()
            val email = binding.inputEmail.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                //val clienteExample = ClienteExample(0, name, phone, email)
                val cliente = Cliente(id = 0, name = name, phone = phone, email = email)
                clientViewModel.insertClient(cliente = cliente)
                Log.d("AddFragment", "usuario creado! $cliente")
                findNavController().navigate(R.id.action_addFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "Complete todos los campos!", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }
}