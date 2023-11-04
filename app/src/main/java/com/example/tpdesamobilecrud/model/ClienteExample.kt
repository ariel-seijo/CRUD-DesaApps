package com.example.tpdesamobilecrud.model

import java.io.Serializable

data class ClienteExample (
    val id: Int,
    val name: String,
    val phone: String,
    val email: String
) : Serializable
