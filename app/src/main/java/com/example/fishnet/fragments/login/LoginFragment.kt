package com.example.fishnet.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishnet.R
import com.example.fishnet.databinding.FragmentLearnFishesBinding
import com.example.fishnet.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    var binding :FragmentLoginBinding? = null

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

        binding?.loginButton?.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
        }

        binding?.registerButton?.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}