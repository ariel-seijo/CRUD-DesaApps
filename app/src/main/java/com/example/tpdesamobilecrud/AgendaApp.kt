package com.example.tpdesamobilecrud

import android.app.Application

class AgendaApp: Application() {

    companion object {
        lateinit var instance: AgendaApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}