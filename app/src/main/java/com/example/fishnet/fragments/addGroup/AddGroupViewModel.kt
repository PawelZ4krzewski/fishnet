package com.example.fishnet.fragments.addGroup

import androidx.lifecycle.ViewModel
import com.example.fishnet.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow

class AddGroupViewModel : ViewModel() {
    private val _groupName = MutableStateFlow("")
    private val _groupDescription = MutableStateFlow("")
    private val repository = FirebaseRepository()

    fun addCardGroup(){
        val cardGroup = com.example.fishnet.data.cardGroupData(
            "",
            _groupDescription.value,
            "groupId",
            _groupName.value
        )

        repository.createCardGroup(cardGroup)
    }

    fun setGroupName(groupName: String) {
        _groupName.value = groupName
    }

    fun setGroupDescription(groupDescription: String) {
        _groupDescription.value = groupDescription
    }

    fun getGroupName(): String {
        return _groupName.value
    }

    fun getGroupDescription(): String {
        return _groupDescription.value
    }
}