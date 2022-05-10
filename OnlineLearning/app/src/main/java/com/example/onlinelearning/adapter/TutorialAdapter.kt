package com.example.onlinelearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelearning.model.TutorialData
import com.example.onlinelearning.databinding.TutorialScreenBinding

class TutorialAdapter(private val tutorialData: ArrayList<TutorialData>): RecyclerView.Adapter<TutorialAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TutorialScreenBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TutorialScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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