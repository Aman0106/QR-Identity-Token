package com.example.qridentitytoken.shared_videomodel

import androidx.lifecycle.ViewModel
import com.example.qridentitytoken.feature_auth.data.UserData

object UserSharedViewModel: ViewModel() {

    private var userData: UserData? = null

    fun setSignedInUser(signedInUserData: UserData) {
        userData = signedInUserData
    }

    fun getSignedInUserData() = userData

}