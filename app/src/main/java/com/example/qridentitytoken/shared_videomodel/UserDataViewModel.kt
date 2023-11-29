package com.example.qridentitytoken.shared_videomodel

import androidx.lifecycle.ViewModel
import com.example.qridentitytoken.feature_auth.data.UserData
import com.example.qridentitytoken.feature_auth.data.UserGeneratedData

object UserDataViewModel: ViewModel() {

    private var userData: UserData? = null
    private var userGeneratedData: UserGeneratedData? = null

    fun setSignedInUser(signedInUserData: UserData) {
        userData = signedInUserData
    }

    fun getSignedInUserData() = userData
    fun setUserGeneratedData(updatedUserGeneratedData: UserGeneratedData) {
        userGeneratedData = updatedUserGeneratedData
    }

    fun getUserGeneratedData() = userGeneratedData
}