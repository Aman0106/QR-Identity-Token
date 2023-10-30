package com.example.qridentitytoken.feature_auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)