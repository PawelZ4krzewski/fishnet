package com.example.fishnet.fragments.listFishes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fishnet.repository.FirebaseRepository

class ListFishesViewModelFactory(val groupId: String) : ViewModelProvider.Factory {



        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListFishesViewModel(groupId) as T
        }


}