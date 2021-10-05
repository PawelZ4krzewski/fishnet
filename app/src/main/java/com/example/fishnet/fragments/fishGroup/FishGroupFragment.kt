package com.example.fishnet.fragments.fishLearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fishnet.databinding.ItemFishGroupBinding


class FishGroupFragment : Fragment() {

    private var binding: ItemFishGroupBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemFishGroupBinding.inflate(inflater, container, false)
        return binding?.root
    }

}