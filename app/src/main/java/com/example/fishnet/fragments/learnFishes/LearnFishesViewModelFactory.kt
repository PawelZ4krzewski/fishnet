package com.example.fishnet.fragments.learnFishes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fishnet.fragments.listFishes.ListFishesViewModel

class LearnFishesViewModelFactory(val groupId: String) : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LearnFishesViewModel(groupId) as T
    }


}