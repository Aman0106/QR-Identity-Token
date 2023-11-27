package com.example.qridentitytoken.feature_home.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.qridentitytoken.shared_videomodel.UserSharedViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object UserItemsRepository {

    private val userItems = mutableListOf<UserItem>()

    fun getUserItems() = userItems

    fun addUserItem(userItem: UserItem) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users")
            .document(UserSharedViewModel.getSignedInUserData()!!.userId)
            .collection("items")
            .document(userItem.itemName)

        docRef
            .set(hashMapOf(
                "contact" to userItem.contact
            ))
            .addOnSuccessListener {
                Log.d(TAG, "File added Successfully")
            }

        userItems.add(userItem)
    }

    suspend fun fetchUserItems(): MutableList<UserItem> {
        val db = Firebase.firestore
        val docRef = db.collection("users").document(UserSharedViewModel.getSignedInUserData()!!.userId).collection("items")

        docRef.get()
            .addOnSuccessListener { result ->

                val userItemsList = mutableListOf<UserItem>()
                if (result.isEmpty)
                    return@addOnSuccessListener
                for (doc in result) {
                    Log.d(TAG, "${doc.id} => ${doc.data}")
                    val userItem = UserItem(
                        itemName = doc.id,
                        contact = doc.get("contact") as String
                    )
                    userItemsList += userItem
                }

                userItems.clear()
                userItems.addAll(userItemsList)
            }
            .addOnFailureListener { exception ->
                Log.e("HomeScreen", "Failed to get document", exception)
            }.await()
        Log.e(TAG, userItems.size.toString())
        return userItems
    }


    val dummyList = listOf(
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
        UserItem("Pen", contact = "aman"),
    )

}