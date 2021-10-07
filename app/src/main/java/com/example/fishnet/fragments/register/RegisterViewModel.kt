package com.example.fishnet.fragments.register


import androidx.lifecycle.ViewModel
import com.example.fishnet.data.UserData
import com.example.fishnet.repository.FirebaseRepository

class RegisterViewModel: ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: UserData){
        repository.createNewUser(user)
    }
}