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

    private val  tvPatnerTitleP: TextView by lazy{findViewById(R.id.tvPatnerTitle)}
    private val  cbDoneP: CheckBox by lazy{findViewById(R.id.cbDone)}

    class PatnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatnerViewHolder {
        return PatnerViewHolder(
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
            tvPatnerTitleP.paintFlags=tvPatnerTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvPatnerTitleP.paintFlags=tvPatnerTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: PatnerViewHolder, position: Int) {
        val curPatner = patners[position]
        holder.itemView.apply {
            //val tvPatnerTitle= findViewById(R.id.tvPatnerTitle)
            tvPatnerTitleP.text=curPatner.title
            //val cbDone=findViewById(R.id.cbDone)
            cbDoneP.isChecked=curPatner.isChecked
            toggleStrikeThrough(tvPatnerTitleP,curPatner.isChecked)
            cbDoneP.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvPatnerTitleP,isChecked)
                curPatner.isChecked= !curPatner.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return patners.size
    }
}