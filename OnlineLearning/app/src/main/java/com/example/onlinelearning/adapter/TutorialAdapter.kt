package com.example.onlinelearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelearning.databinding.ItemTutorialScreenBinding
import com.example.onlinelearning.model.TutorialData

class TutorialAdapter(private val tutorialData: ArrayList<TutorialData>): RecyclerView.Adapter<TutorialAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTutorialScreenBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTutorialScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(tutorialData[position]) {
                binding.imgTutorial.setImageResource(image)
                binding.tvHeader.text = header
                binding.tvDescription.text = description
            }
        }
    }

    override fun getItemCount(): Int {
        return tutorialData.count()
    }
}