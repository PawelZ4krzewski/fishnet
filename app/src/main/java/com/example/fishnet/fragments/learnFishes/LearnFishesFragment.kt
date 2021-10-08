package com.example.fishnet.fragments.learnFishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.fishnet.adapters.FishLearnRvAdapter
import com.example.fishnet.databinding.FragmentLearnFishesBinding
import com.example.fishnet.fragments.listFishes.ListFishesViewModel
import com.example.fishnet.fragments.listFishes.ListFishesViewModelFactory
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod

class LearnFishesFragment : Fragment(), CardStackListener{

    private val args: LearnFishesFragmentArgs by navArgs()
    private lateinit var layoutManager: CardStackLayoutManager
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

        layoutManager = CardStackLayoutManager(requireContext(), this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){
        binding.learnFishesRecyclerView.layoutManager = layoutManager
        binding.learnFishesRecyclerView.adapter = adapter
        binding.learnFishesRecyclerView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        learnFishesVM.flashCardList.observe(viewLifecycleOwner,{
            adapter.setFlashCards(it)
        })
//        val snapHelper = LinearSnapHelper().attachToRecyclerView(binding.learnFishesRecyclerView)

    }
}