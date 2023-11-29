
package com.example.qridentitytoken.feature_auth.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.qridentitytoken.feature_auth.SignInState
import com.example.qridentitytoken.feature_auth.data.SignInResult
import com.example.qridentitytoken.shared_videomodel.UserDataViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun setDefaultUserData() {
        val firestoreDB = Firebase.firestore

        val fieldsToAdd = hashMapOf<String, String >()
        val docRef = firestoreDB.collection("users").document(UserDataViewModel.getSignedInUserData()!!.userId)

        docRef.get().addOnSuccessListener {
            Log.d(TAG, "added poooooooo")
            if (!it.contains("userName"))
                fieldsToAdd["userName"] = UserDataViewModel.getSignedInUserData()!!.userName
            if (!it.contains("userEmail"))
                fieldsToAdd["userEmail"] = UserDataViewModel.getSignedInUserData()!!.userEmail
            docRef.set(fieldsToAdd)
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}