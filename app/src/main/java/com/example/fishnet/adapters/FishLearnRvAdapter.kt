package com.example.fishnet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishnet.R
import com.example.fishnet.data.FishData

class FishLearnRvAdapter: RecyclerView.Adapter<FishLearnRvAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_learn_fish, parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FishLearnRvAdapter.MyViewHolder, position: Int) {
        holder.termTextView.text = FishData.term[position]
        holder.definitionTextView.text = FishData.definition[position]
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

    override fun getItemCount() = FishData.term.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val termTextView = itemView.findViewById<TextView>(R.id.termLearnFishTextView)
        val definitionTextView = itemView.findViewById<TextView>(R.id.definitionLearnFishTextView)
    }
}