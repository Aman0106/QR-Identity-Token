package com.example.qridentitytoken.feature_auth.data

import com.example.qridentitytoken.R

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
)
