package com.example.tpdesamobilecrud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.tpdesamobilecrud.model.Cliente
import com.example.tpdesamobilecrud.repository.ClientRepository
import kotlinx.coroutines.launch


class ClientViewModel() : ViewModel() {

    private val repository = ClientRepository()

    val readAllData: LiveData<List<Cliente>> = repository.readAllData


    fun insertClient(cliente: Cliente) {
        viewModelScope.launch { //(Dispatchers.IO)
            repository.insertClient(client = cliente)
        }
    }


    fun updateClient(client: Cliente) {
        viewModelScope.launch {
            repository.updateClient(client = client)
        }
    }


    fun deleteClient(client: Cliente) {
        viewModelScope.launch {
            repository.deleteClient(client = client)
        }
    }


    fun deleteAllClients() {
        viewModelScope.launch {
            repository.deleteAllClients()
        }
    }

}