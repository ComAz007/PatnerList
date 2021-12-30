package com.example.patnerlist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PatnerAdapter (
      private val patners: MutableList<Patner>
          ): RecyclerView.Adapter<PatnerAdapter.PatnerViewHolder>() {



    class PatnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tvPatnerTitleP: TextView = itemView.findViewById(R.id.tvPatnerTitle)
        val cbDoneP: CheckBox = itemView.findViewById(R.id.cbDone)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatnerAdapter.PatnerViewHolder {
        return PatnerAdapter.PatnerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_patner,
                parent,
                false
            )
        )
    }


    fun addPatner(patner:Patner){
        patners.add(patner)
        notifyItemInserted(patners.size-1)
    }

    fun deleteDonePatner(){
        patners.removeAll { patner ->
            patner.isChecked  }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvPatnerTitle: TextView, isChecked: Boolean)
    {
        if (isChecked){
            tvPatnerTitle.paintFlags=tvPatnerTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvPatnerTitle.paintFlags=tvPatnerTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: PatnerAdapter.PatnerViewHolder, position: Int) {
        val curPatner = patners[position]
        holder.itemView.apply {
            //val tvPatnerTitle= findViewById(R.id.tvPatnerTitle)
            holder.tvPatnerTitleP.text=curPatner.title
            //val cbDone=findViewById(R.id.cbDone)
            holder.cbDoneP.isChecked=curPatner.isChecked
            toggleStrikeThrough(holder.tvPatnerTitleP,curPatner.isChecked)
            holder.cbDoneP.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(holder.tvPatnerTitleP,isChecked)
                curPatner.isChecked= !curPatner.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return patners.size
    }
}