package com.example.tpdesamobilecrud.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tpdesamobilecrud.model.Cliente


@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(client: Cliente)

    @Query("SELECT * FROM client_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cliente>>

    @Update
    suspend fun update(client: Cliente)

    @Delete
    suspend fun deleteClient(client: Cliente)

    @Query("DELETE FROM client_table")
    suspend fun deleteAll()
}