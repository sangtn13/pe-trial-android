package com.qe170193.data.model

data class Land(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val details: String
)
