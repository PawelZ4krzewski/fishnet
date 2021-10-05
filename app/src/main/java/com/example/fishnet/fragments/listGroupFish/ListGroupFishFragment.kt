package com.example.fishnet.fragments.listGroupFish

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishnet.R
import com.example.fishnet.adapters.FishGroupRvAdapter
import com.example.fishnet.adapters.FishesRvAdapter
import com.example.fishnet.data.cardGroupData
import com.example.fishnet.databinding.FragmentListGroupFishBinding
import androidx.navigation.fragment.findNavController

class ListGroupFishFragment : Fragment(), FishGroupRvAdapter.OnCardGroupItemLongClick {


    private var _binding: FragmentListGroupFishBinding? = null
    private val binding
            get() = _binding!!
    private val listGroupFishVM by viewModels<ListGroupFishViewModel>()
    private val adapter =  FishGroupRvAdapter(this)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListGroupFishBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LGF_DEBUG","Przed setupRecycler View")
        setupRecyclerView()
    }
    private fun setupRecyclerView(){
        binding.cardGroupRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cardGroupRecyclerView.adapter = adapter
        Log.d("LGF_DEBUG","W  setupRecycler View")

        listGroupFishVM.cardGroup.observe(viewLifecycleOwner,{
            Log.d("LGF_DEBUG",it.toString())
            adapter.setCardGroup(it)
        })
    }

    override fun onCardGroupItemLongClick(cardGroup: cardGroupData, position: Int) {
        val action = ListGroupFishFragmentDirections.actionFishGroupFragmentToFishesFragment(cardGroup.groupId!!)
        findNavController().navigate(action)
    }
}