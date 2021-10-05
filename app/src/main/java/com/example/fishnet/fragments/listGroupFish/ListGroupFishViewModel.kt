package com.example.fishnet.fragments.listGroupFish

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.fishnet.data.UserData
import com.example.fishnet.repository.FirebaseRepository

class ListGroupFishViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val user: LiveData<UserData> = repository.getUserData()

    val cardGroup = Transformations.switchMap(user){
        repository.getCardGroupData(it.cardGroupsId)
    }

//    val cardGroup = repository.getCardGroupData(listOf("test","test1"))



}

