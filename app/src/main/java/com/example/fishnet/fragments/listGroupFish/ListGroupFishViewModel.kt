package com.example.fishnet.fragments.listGroupFish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fishnet.repository.FirebaseRepository
import com.google.firebase.firestore.auth.User

class ListGroupFishViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    private val user = repository.getUserData()
    val cardGroup = repository.getCardGroupData(listOf("test","test1"))

}

