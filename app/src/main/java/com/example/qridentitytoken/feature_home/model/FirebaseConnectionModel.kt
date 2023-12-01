package com.example.qridentitytoken.feature_home.model

import com.example.qridentitytoken.shared_videomodel.UserDataViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseConnectionModel {
    val firebaseFirestore = FirebaseFirestore.getInstance()

    fun updateUserInfo() {
        val userDocRef = firebaseFirestore
            .collection("users")
            .document(UserDataViewModel.getSignedInUserData()!!.userId)
        val userGeneratedData = UserDataViewModel.getUserGeneratedData()
        userDocRef.set(
            hashMapOf(
                "userName" to userGeneratedData!!.userName,
                "userEmail" to userGeneratedData.userEmail,
                "userContactNumber" to userGeneratedData.userContactNumber,
            )
        )
    }
}