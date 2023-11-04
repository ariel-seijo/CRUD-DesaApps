package com.example.tpdesamobilecrud.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tpdesamobilecrud.AgendaApp
import com.example.tpdesamobilecrud.constants.Constants
import com.example.tpdesamobilecrud.model.Cliente

@Database(entities = [Cliente::class], version = 1, exportSchema = false)
abstract class ClientDB : RoomDatabase() {

    abstract fun clientDao(): ClienteDao

    companion object {
        @Volatile
        private var INSTANCE: ClientDB? = null

        fun getDatabase(): ClientDB {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    AgendaApp.instance.applicationContext,
                    ClientDB::class.java,
                    Constants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }

        }
    }
}