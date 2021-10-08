package com.example.fishnet.fragments.profile

import androidx.lifecycle.ViewModel
import com.example.fishnet.repository.FirebaseRepository

class ProfileViewModel: ViewModel() {
    private val repository = FirebaseRepository()
    val user = repository.getUserData()

}