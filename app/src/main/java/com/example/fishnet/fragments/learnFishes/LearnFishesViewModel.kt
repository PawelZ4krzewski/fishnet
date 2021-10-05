package com.example.fishnet.fragments.learnFishes

import androidx.lifecycle.ViewModel
import com.example.fishnet.repository.FirebaseRepository

class LearnFishesViewModel (private val groupId: String) : ViewModel(){
    private val repository = FirebaseRepository()
    val flashCardList = repository.getFlashCardsData(groupId)
    val user = repository.getUserData()
}