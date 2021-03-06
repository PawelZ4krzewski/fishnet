package com.example.fishnet.data

data class UserData(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val cardGroupsId: List<String>? = null
)