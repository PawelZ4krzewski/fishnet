package com.example.fishnet.adapters

import android.app.ActionBar
import android.content.ContentValues.TAG
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishnet.R
import com.example.fishnet.data.FishData

class FishesRvAdapter(
//    val fishes:ArrayList<FishData>,
//    val listener: (FishData) -> Unit
) : RecyclerView.Adapter<FishesRvAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fish, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val fish = fishes[position]
        holder.termTextView.text = FishData.term[position]
        holder.definitionTextView.text = FishData.definition[position]
        holder.itemView.setOnClickListener{
            Log.d(TAG, "Kliknales na E")
            Log.d(TAG, holder.fishesImage.layoutParams.height.toString())

            if(holder.fishesImage.layoutParams.height == 0)
            {
                Log.d(TAG, "Rozwijanie obrazka")
                holder.fishesImage.layoutParams.height = ActionBar.LayoutParams.WRAP_CONTENT
                Log.d(TAG, holder.fishesImage.layoutParams.height.toString())
                notifyItemChanged(position)
            }
            else
            {
                Log.d(TAG, "Chowanie obrazka")
                holder.fishesImage.layoutParams.height = 0
                Log.d(TAG, holder.fishesImage.layoutParams.height.toString())
                notifyItemChanged(position)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = FishData.term.size


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val termTextView = itemView.findViewById<TextView>(R.id.termTextView)
        val definitionTextView = itemView.findViewById<TextView>(R.id.definitionTextView)
        val fishesImage = itemView.findViewById<View>(R.id.fishesImages)
    }

}

