package com.example.qridentitytoken.feature_auth.data

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val profilePictureUrl: String?,
)

data class UserGeneratedData(
    val userName: String,
    val userEmail: String,
    val userProfilePictureUrl: String?,
    val userContactNumber: String?
)
