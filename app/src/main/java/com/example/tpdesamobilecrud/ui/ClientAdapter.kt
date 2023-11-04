package com.example.tpdesamobilecrud.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tpdesamobilecrud.R
import com.example.tpdesamobilecrud.model.ClienteExample
import com.example.tpdesamobilecrud.databinding.ItemRecyclerviewClienteBinding
import com.example.tpdesamobilecrud.model.Cliente


class ClienteAdapter: RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    private var clientList = emptyList<Cliente>()

    inner class ClienteViewHolder(private val binding: ItemRecyclerviewClienteBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(cliente: Cliente) {
            with(binding) {
                tvId.text = cliente.id.toString()
                tvName.text = cliente.name
                tvPhone.text = cliente.phone
                tvEmail.text = cliente.email

                root.setOnClickListener {

                    val bundle = Bundle()
                    bundle.putSerializable("cliente", cliente)
                    itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
                }
            }
//            binding.tvId.text = user.id.toString()
//            binding.tvName.text = user.name
//            binding.tvLastName.text = user.lastName
//            binding.tvAge.text = user.age.toString()
//            binding.root.setOnClickListener {  }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val binding = ItemRecyclerviewClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClienteViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val client = clientList.get(position)
        holder.bind(cliente = client)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setList(clients: List<Cliente>) {
        clientList = clients
        notifyDataSetChanged()
    }


}