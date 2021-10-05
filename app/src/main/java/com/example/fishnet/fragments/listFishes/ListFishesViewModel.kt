package com.example.fishnet.fragments.listFishes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fishnet.repository.FirebaseRepository

class ListFishesViewModel(private val groupId: String) : ViewModel() {

    private val repository = FirebaseRepository()
    val flashCardList = repository.getFlashCardsData(groupId)
    val user = repository.getUserData()

}