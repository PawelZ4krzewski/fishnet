package com.example.fishnet.fragments

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fishnet.activities.MainActivity

abstract class BaseFragment(val fragment: Int): Fragment(fragment) {
    protected fun startApp(){
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}