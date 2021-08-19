package com.example.fishnet.fragments.fish

import android.app.ActionBar
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fishnet.R
import com.example.fishnet.databinding.ItemFishBinding

class FishFragment: Fragment(R.layout.item_fish) {

    private var binding: ItemFishBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemFishBinding.inflate(inflater, container, false)
        return binding?.root
    }

}