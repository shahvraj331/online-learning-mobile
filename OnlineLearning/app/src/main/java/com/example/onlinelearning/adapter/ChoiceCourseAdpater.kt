package com.example.onlinelearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.databinding.ItemChoiceCourseBinding

class ChoiceCourseAdapter: RecyclerView.Adapter<ChoiceCourseAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemChoiceCourseBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChoiceCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { }

    override fun getItemCount(): Int {
        return Constants.SIX
    }
}