package com.example.qridentitytoken.feature_home.data

object UserItemsRepository {

    private val userItems = mutableListOf<UserItem>()

    fun getUserItems() = userItems

    fun addUserItem(userItem: UserItem) {
        userItems.add(userItem)
    }

    fun fetchUserItems() {
        // Connect to firebase
    }
}