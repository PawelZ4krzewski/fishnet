package com.example.fishnet.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fishnet.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val fbAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        isCurrentUser()
    }

    private fun isCurrentUser(){
        fbAuth.currentUser?.let { auth ->
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            startActivity(intent)
        }
    }

}