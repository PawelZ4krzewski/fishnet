package com.example.fishnet.fragments.learnFishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.fishnet.adapters.FishLearnRvAdapter
import com.example.fishnet.databinding.FragmentLearnFishesBinding
import com.example.fishnet.fragments.listFishes.ListFishesViewModel
import com.example.fishnet.fragments.listFishes.ListFishesViewModelFactory

class LearnFishesFragment : Fragment() {

    private val args: LearnFishesFragmentArgs by navArgs()

    private val adapter = FishLearnRvAdapter()

    private var _binding: FragmentLearnFishesBinding? = null
    private val binding
        get()=_binding!!

    private val learnFishesVM: LearnFishesViewModel by viewModels{ LearnFishesViewModelFactory(args.groupId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearnFishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        binding.learnFishesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.learnFishesRecyclerView.adapter = adapter
        learnFishesVM.flashCardList.observe(viewLifecycleOwner,{
            adapter.setFlashCards(it)
        })
        val snapHelper = LinearSnapHelper().attachToRecyclerView(binding.learnFishesRecyclerView)

    }
}