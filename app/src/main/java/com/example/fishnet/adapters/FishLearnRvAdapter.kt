package com.example.fishnet.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishnet.R
import com.example.fishnet.data.FishData
import com.example.fishnet.data.FlashCardData

class FishLearnRvAdapter: RecyclerView.Adapter<FishLearnRvAdapter.MyViewHolder>() {

    val flashCards = ArrayList<FlashCardData>()

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
        holder.itemView.setOnClickListener(){
            if(holder.termTextView.visibility == View.INVISIBLE) {
                holder.termTextView.visibility = View.VISIBLE
                holder.definitionTextView.visibility = View.INVISIBLE
            }
            else{
                holder.termTextView.visibility = View.INVISIBLE
                holder.definitionTextView.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = flashCards.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val termTextView = itemView.findViewById<TextView>(R.id.termLearnFishTextView)
        val definitionTextView = itemView.findViewById<TextView>(R.id.definitionLearnFishTextView)
    }
}