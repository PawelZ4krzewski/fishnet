package com.example.fishnet.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fishnet.R
import com.example.fishnet.data.UserData
import com.example.fishnet.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private val profileVM: ProfileViewModel by viewModels<ProfileViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding
            get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVM.user.observe(viewLifecycleOwner,{user ->
            bindUserData(user)
        })
    }

    private fun bindUserData(user: UserData) {
        binding.userName.text = user.name
        binding.userEmail.text = user.email
    }


}