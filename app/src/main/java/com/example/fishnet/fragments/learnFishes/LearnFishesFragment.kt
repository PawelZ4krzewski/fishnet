package com.example.fishnet.fragments.learnFishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.fishnet.adapters.FishLearnRvAdapter
import com.example.fishnet.databinding.FragmentLearnFishesBinding

class LearnFishesFragment : Fragment() {

    var binding: FragmentLearnFishesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnFishesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        binding?.learnFishesRecyclerView?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding?.learnFishesRecyclerView?.adapter = FishLearnRvAdapter()

        val snapHelper = LinearSnapHelper().attachToRecyclerView(binding?.learnFishesRecyclerView)

    }
}