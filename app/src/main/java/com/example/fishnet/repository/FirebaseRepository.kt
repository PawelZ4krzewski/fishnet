package com.example.fishnet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fishnet.data.FlashCardData
import com.example.fishnet.data.UserData
import com.example.fishnet.data.cardGroupData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {

    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()


    fun createNewUser(user: UserData){
        cloud.collection("user")
            .document(user.userId!!)
            .set(user)
    }

    fun getUserData(): LiveData<UserData> {
        val cloudResult = MutableLiveData<UserData>()
        val userId = auth.currentUser?.uid

        cloud.collection("user")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(UserData::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getFlashCardsData(groupId: String) :LiveData<List<FlashCardData>> {
        val cloudResult = MutableLiveData<List<FlashCardData>>()
        cloud.collection("flashCard")
            .whereEqualTo("groupId",groupId)
            .get()
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, it.toString())
                val flashCard = it.toObjects(FlashCardData::class.java)
                Log.d(REPO_DEBUG, flashCard.toString())
                cloudResult.postValue(flashCard)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getCardGroupData(list: List<String>?) :LiveData<List<cardGroupData>> {
        val cloudResult = MutableLiveData<List<cardGroupData>>()

        if(!list.isNullOrEmpty()) {
            cloud.collection("cardGroup")
                .whereIn("groupId", list)
                .get()
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, it.toString())
                    val cardGroup = it.toObjects(cardGroupData::class.java)
                    Log.d(REPO_DEBUG, cardGroup.toString())
                    cloudResult.postValue(cardGroup)
                }
                .addOnFailureListener {
                    Log.d(REPO_DEBUG, it.message.toString())
                }
        }
        return cloudResult
    }
}