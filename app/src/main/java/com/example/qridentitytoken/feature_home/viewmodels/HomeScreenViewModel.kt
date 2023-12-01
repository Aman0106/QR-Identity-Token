package com.example.qridentitytoken.feature_home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.data.UserItemsRepository
import com.example.qridentitytoken.feature_home.model.FirebaseConnectionModel

object HomeScreenViewModel: ViewModel() {
    private var bottomSheetOpenState by mutableStateOf(false)
    private var currentUserItem: UserItem? = null
    fun openBottomSheet() {
        bottomSheetOpenState = true
    }

    fun closeBottomSheet() {
        bottomSheetOpenState = false
    }

    fun isBottomSheetOpen(): Boolean {
        return bottomSheetOpenState
    }

    fun getCurrentUserItem() = currentUserItem
    fun setCurrentUserItem(userItem: UserItem) {
        currentUserItem = userItem
    }
    fun submitItemDetails(userItem: UserItem){
        setCurrentUserItem(userItem)
         UserItemsRepository.addUserItem(userItem)
    }

    fun updateUserDetails() {
        FirebaseConnectionModel().updateUserInfo()
    }
}