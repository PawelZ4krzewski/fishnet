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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishnet.R
import com.example.fishnet.adapters.FishesRvAdapter
import com.example.fishnet.data.FlashCardData
import com.example.fishnet.data.UserData
import com.example.fishnet.databinding.FragmentFishesBinding
import com.example.fishnet.fragments.listFishes.ListFishesViewModel
import com.example.fishnet.fragments.listFishes.ListFishesViewModelFactory
import com.example.fishnet.fragments.listGroupFish.ListGroupFishFragmentDirections

class FishesFragment : Fragment() {

    private val FC_DEBUG = "FC_DEBUG"

    private val args: FishesFragmentArgs by navArgs()

    private var _binding:FragmentFishesBinding? = null
    private val binding
        get() = _binding!!

    private val listFishesVM:ListFishesViewModel by viewModels{ ListFishesViewModelFactory(args.groupId) }

    private val adapter =  FishesRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFishesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.learnButton.setOnClickListener() {
            val action = FishesFragmentDirections.actionFishesFragmentToLearnFishFragment(args.groupId)
            findNavController().navigate(action)
        }

        binding.addFlashCardButton.setOnClickListener() {
            val action = FishesFragmentDirections.actionFishesFragmentToAddFlashCardFragment(args.groupId)
            findNavController().navigate(action)
        }

        Log.d("LF_DEBUG","Group Id "+args.groupId.toString())
        Log.d("LF_DEBUG","Przed setupRecycler View")
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listFishesVM.user.observe(viewLifecycleOwner,{user ->
            bindUserData(user)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun bindUserData(user : UserData) {
        Log.d(FC_DEBUG, user.toString())
    }


    private fun setupRecyclerView(){
        binding.fishesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.fishesRecyclerView.adapter = adapter
        listFishesVM.flashCardList.observe(viewLifecycleOwner,{
            it?.let {
                adapter.setFlashCards(it)
            }

        })
    }
}