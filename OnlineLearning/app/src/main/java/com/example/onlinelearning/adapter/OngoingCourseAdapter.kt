package com.example.onlinelearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.databinding.ItemOngoingCourseBinding

class OngoingCourseAdapter: RecyclerView.Adapter<OngoingCourseAdapter.ViewHolder>() {

    private val lightGreenCardBackground = R.drawable.ic_home_title_background
    private val darkGreenCardBackground = R.drawable.ic_ongoing_course_background

    inner class ViewHolder(val binding: ItemOngoingCourseBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOngoingCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.imgBackground.setBackgroundResource(if (position % 2 == 0) lightGreenCardBackground else darkGreenCardBackground)
        }
    }

    override fun getItemCount(): Int {
        return Constants.FOUR
    }
}