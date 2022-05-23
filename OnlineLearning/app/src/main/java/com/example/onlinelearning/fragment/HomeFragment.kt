package com.example.onlinelearning.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinelearning.R
import com.example.onlinelearning.adapter.ChoiceCourseAdapter
import com.example.onlinelearning.adapter.OngoingCourseAdapter
import com.example.onlinelearning.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding) {
            imgHeader.clipToOutline = true
            rvOngoingCourse.adapter = OngoingCourseAdapter()
            rvChoiceCourse.adapter = ChoiceCourseAdapter()
            setUpTabBar()
        }

        return binding.root
    }

    private fun setUpTabBar() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.all)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.popular)))
    }
}