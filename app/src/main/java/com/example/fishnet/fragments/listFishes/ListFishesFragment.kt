package com.example.fishnet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishnet.R
import com.example.fishnet.adapters.FishesRvAdapter
import com.example.fishnet.databinding.FragmentFishesBinding

class FishesFragment : Fragment() {

    var binding:FragmentFishesBinding? = null

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

    private fun setupRecyclerView(){
        binding?.fishesRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.fishesRecyclerView?.adapter = FishesRvAdapter()
    }
}