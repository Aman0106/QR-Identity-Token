package com.example.qridentitytoken.feature_home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {
    private var bottomSheetOpenState by mutableStateOf(false)
    fun openBottomSheet() {
        bottomSheetOpenState = true
    }

    fun closeBottomSheet() {
        bottomSheetOpenState = false
    }

    fun isBottomSheetOpen(): Boolean {
        return bottomSheetOpenState
    }
}