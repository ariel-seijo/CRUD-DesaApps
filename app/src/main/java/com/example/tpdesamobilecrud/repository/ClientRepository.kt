package com.example.tpdesamobilecrud.repository

import androidx.lifecycle.LiveData
import com.example.tpdesamobilecrud.data.ClientDB
import com.example.tpdesamobilecrud.model.Cliente


class ClientRepository {


    private val clientDao = ClientDB.getDatabase().clientDao()

    val readAllData: LiveData<List<Cliente>> = clientDao.readAllData()


    suspend fun insertClient(client: Cliente) {
        clientDao.insert(client = client)
    }


    suspend fun updateClient(client: Cliente) {
        clientDao.update(client = client)
    }


    suspend fun deleteClient(client: Cliente) {
        clientDao.deleteClient(client = client)
    }

    suspend fun deleteAllClients() {
        clientDao.deleteAll()
    }
}