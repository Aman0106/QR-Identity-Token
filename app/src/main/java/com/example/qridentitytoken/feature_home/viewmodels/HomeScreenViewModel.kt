package com.example.qridentitytoken.feature_home.viewmodels

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.qridentitytoken.feature_home.data.UserItem
import com.example.qridentitytoken.feature_home.data.UserItemsRepository

class HomeScreenViewModel: ViewModel() {
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

    fun submitItemDetails(userItem: UserItem){
        currentUserItem = userItem
         UserItemsRepository.addUserItem(userItem)
    }
}