package com.example.fishnet.adapters

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.fishnet.R
import com.example.fishnet.data.FishData
import com.example.fishnet.data.FlashCardData

class FishLearnRvAdapter: RecyclerView.Adapter<FishLearnRvAdapter.MyViewHolder>() {

    val flashCards = ArrayList<FlashCardData>()
    private lateinit var flip_in: AnimatorSet
    private lateinit var flip_out: AnimatorSet

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_learn_fish, parent , false)

        return MyViewHolder(view)
    }

    fun setFlashCards(list : List<FlashCardData>){
        Log.d("FRV_DEBUG",list.toString())
        flashCards.clear()
        flashCards.addAll(list)
        Log.d("FRV_DEBUG",flashCards.size.toString())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FishLearnRvAdapter.MyViewHolder, position: Int) {
        val flashCard = flashCards[position]
        holder.termTextView.text = flashCard.question
        holder.definitionTextView.text = flashCard.answer
        holder.flashCardLayout.setOnClickListener {
            val isQuestion = holder.termTextView.visibility
            Log.d("FRV_DEBUG", "Czy jest visible termTextView "+isQuestion.toString())
            if (isQuestion == 8) {
                Log.d("FRV_DEBUG", "1. termTextView "+ isQuestion.toString() + " definitionTextView "+holder.definitionTextView.visibility.toString())
                flipCard(holder.view.context, holder.termTextView, holder.definitionTextView)
            } else {
                Log.d("FRV_DEBUG", "2. termTextView "+ isQuestion.toString() + " definitionTextView "+holder.definitionTextView.visibility.toString())
                flipCard(holder.view.context, holder.definitionTextView, holder.termTextView)
            }
        }
    }

    override fun getItemCount() = flashCards.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val termTextView = itemView.findViewById<TextView>(R.id.termLearnFishTextView)
        val view = itemView
        val definitionTextView = itemView.findViewById<TextView>(R.id.definitionLearnFishTextView)
        val flashCardLayout = itemView.findViewById<ConstraintLayout>(R.id.learnCardConstraintLayout)
    }

//    fun flipCard(view: View)
//    {
//        val scale = view.context.resources.displayMetrics.density
//        flip_in = AnimatorInflater.loadAnimator(view.context,R.animator.flip_in) as AnimatorSet
//        flip_out = AnimatorInflater.loadAnimator(view.context,R.animator.flip_out) as AnimatorSet
//
//
//    }

    fun flipCard(context: Context, visibleView: View, inVisibleView: View) {
        try {
            Log.d("FRV_DEBUG","visibleView PRZED: "+visibleView.visibility.toString())
            visibleView.visibility = View.VISIBLE
            Log.d("FRV_DEBUG","visibleView PO: "+visibleView.visibility.toString())
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist

            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)

            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)

            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()
            flipInAnimatorSet.doOnEnd {
                inVisibleView.visibility = View.GONE
            }
        } catch (e: Exception) {
            Log.d("FRV_DEBUG", e.toString())
        }
    }

}