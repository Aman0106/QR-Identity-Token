package com.example.qridentitytoken.feature_home.data

data class UserItem(
    val name: String,
    val description: String? = null,
    val contact: String,
    val secondaryContact: String? = null,
)
