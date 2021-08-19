package com.example.fishnet.fragments.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fishnet.R
import com.example.fishnet.activities.MainActivity
import com.example.fishnet.databinding.ActivityLoginBinding.inflate
import com.example.fishnet.databinding.FragmentRegisterBinding
import com.example.fishnet.fragments.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private var binding: FragmentRegisterBinding? = null
    private val fbAuth = FirebaseAuth.getInstance()
    private val REG_DEBUG = "REG_DEBUG"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSignUp()
    }

    private fun setupSignUp(){
        binding?.registerButton?.setOnClickListener(){
            val email = binding?.emailInputEditText?.text?.trim().toString()
            val password = binding?.passwordInputEditText?.text?.trim().toString()
            val passwordRepeat = binding?.repeatPasswordInputEditText?.text?.trim().toString()

            if(password == passwordRepeat){
                fbAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener {authRes->
                        if(authRes.user != null) startApp()
                    }.addOnFailureListener {
                        Snackbar.make(
                            requireView(),
                            "Incorrect login or password",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.d("REG_DEBUG", it.message.toString())
                    }
            }
        }
    }

}