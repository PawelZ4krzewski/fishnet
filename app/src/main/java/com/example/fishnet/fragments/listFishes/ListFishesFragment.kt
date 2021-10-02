package com.example.fishnet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishnet.R
import com.example.fishnet.adapters.FishesRvAdapter
import com.example.fishnet.data.FlashCardData
import com.example.fishnet.data.UserData
import com.example.fishnet.databinding.FragmentFishesBinding
import com.example.fishnet.fragments.listFishes.ListFishesViewModel

class FishesFragment : Fragment() {

    private val FC_DEBUG = "FC_DEBUG"

    private var binding:FragmentFishesBinding? = null
    private val listFishesVM by viewModels<ListFishesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFishesBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.learnButton?.setOnClickListener() {
            findNavController().navigate(R.id.action_fishesFragment_to_learnFishFragment)
        }

        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listFishesVM.user.observe(viewLifecycleOwner,{user ->
            bindUserData(user)
        })
    }

    private fun bindUserData(user : UserData) {
        Log.d(FC_DEBUG, user.toString())
    }


    private fun setupRecyclerView(){
        val adapter =  FishesRvAdapter()
        binding?.fishesRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.fishesRecyclerView?.adapter = adapter
        listFishesVM.flashCardList.observe(viewLifecycleOwner,{
            adapter.setFlashCards(it)
        })


    }
}