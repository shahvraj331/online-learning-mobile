package com.example.onlinelearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.databinding.ProfileItemArrowBinding
import com.example.onlinelearning.databinding.ProfileItemSwitchBinding
import com.example.onlinelearning.model.ProfileData

class ProfileAdapter(private val profileData: ArrayList<ProfileData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SwitchViewHolder(val binding: ProfileItemSwitchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder(position: Int) {
            binding.imgProfileItem.setImageResource(profileData[position].image)
            binding.tvItem.text = profileData[position].title
            binding.swDarkMode.isSelected = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
            binding.swDarkMode.setOnCheckedChangeListener { _, isChecked ->
                AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    inner class ArrowViewHolder(val binding: ProfileItemArrowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder(position: Int) {
            binding.imgProfileItem.setImageResource(profileData[position].image)
            binding.tvItem.text = profileData[position].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.ZERO.toInt()) {
            val binding = ProfileItemSwitchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SwitchViewHolder(binding)
        } else {
            val binding = ProfileItemArrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ArrowViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            Constants.ZERO.toInt() -> {
                (holder as SwitchViewHolder).bindViewHolder(position)
            }
            Constants.ONE -> {
                (holder as ArrowViewHolder).bindViewHolder(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return profileData.count()
    }

    override fun getItemViewType(position: Int): Int {
        return profileData[position].viewType
    }
}