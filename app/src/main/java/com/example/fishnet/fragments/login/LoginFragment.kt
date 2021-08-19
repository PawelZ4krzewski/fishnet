package com.example.fishnet.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishnet.R
import com.example.fishnet.activities.MainActivity
import com.example.fishnet.databinding.FragmentLearnFishesBinding
import com.example.fishnet.databinding.FragmentLoginBinding
import com.example.fishnet.fragments.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    var binding :FragmentLoginBinding? = null
    private val fbAuth = FirebaseAuth.getInstance()
    private val LOG_DEBUG = "LOG_DEBUG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupLoginClick()

        binding?.registerButton?.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        setupLoginClick()
    }

    private fun setupLoginClick(){

        binding?.loginButton?.setOnClickListener() {
            val email = binding?.emailTextInputEditText?.text?.trim().toString()
            val password = binding?.passwordTextInputEditText?.text?.trim().toString()
            fbAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authRes ->
                    if (authRes.user != null) startApp()
                }.addOnFailureListener {
                    Snackbar.make(
                        requireView(),
                        "Ups something went wrong...",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.d("LOG_DEBUG", it.message.toString())
                }
        }
    }
}