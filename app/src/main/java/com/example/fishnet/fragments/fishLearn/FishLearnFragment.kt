package com.example.fishnet.fragments.fishLearn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishnet.R
import com.example.fishnet.adapters.FishLearnRvAdapter
import com.example.fishnet.adapters.FishesRvAdapter
import com.example.fishnet.databinding.ItemFishBinding
import com.example.fishnet.databinding.ItemLearnFishBinding


class FishLearnFragment : Fragment() {

    private var _binding: ItemLearnFishBinding? = null
    private val binding
        get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemLearnFishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}