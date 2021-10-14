package com.example.fishnet.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishnet.R
import com.example.fishnet.data.CardGroupData

class FishGroupRvAdapter(private val listener: OnCardGroupItemLongClick) : RecyclerView.Adapter<FishGroupRvAdapter.MyViewHolder>() {

    private val cardGroups = ArrayList<CardGroupData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fish_group, parent, false)
        Log.d("FRV_DEBUG","onCreateViewHolder "+cardGroups.toString())
        return MyViewHolder(view)
    }

    fun setCardGroup(list : List<CardGroupData>){
        Log.d("FGRV_DEBUG",list.toString())
        cardGroups.clear()
        cardGroups.addAll(list)
        Log.d("FGRV_DEBUG",cardGroups.size.toString())
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cardGroup = cardGroups[position]
        holder.groupNameTextView.text = cardGroup.name
        holder.groupDescriptionTextView.text = cardGroup.description
    }

    override fun getItemCount() = cardGroups.size


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnLongClickListener() {
                listener.onCardGroupItemLongClick(cardGroups[adapterPosition], adapterPosition)
                true
            }
        }

        val groupNameTextView = itemView.findViewById<TextView>(R.id.groupNameTextView)
        val groupDescriptionTextView = itemView.findViewById<TextView>(R.id.groupDescriptionTextView)

    }

    interface OnCardGroupItemLongClick{
        fun onCardGroupItemLongClick(cardGroup: CardGroupData, position: Int)
    }


}

