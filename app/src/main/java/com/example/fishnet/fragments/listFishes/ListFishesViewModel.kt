package com.example.fishnet.fragments.listFishes

import androidx.lifecycle.ViewModel
import com.example.fishnet.repository.FirebaseRepository

class ListFishesViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val flashCardList = repository.getFlashCardsData()
    val user = repository.getUserData()
}