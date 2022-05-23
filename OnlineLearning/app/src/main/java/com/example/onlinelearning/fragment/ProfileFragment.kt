package com.example.onlinelearning.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.onlinelearning.adapter.ProfileAdapter
import com.example.onlinelearning.databinding.FragmentProfileBinding
import com.example.onlinelearning.model.ProfileData

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileData: ArrayList<ProfileData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        profileData = ProfileData.getProfileData()
        binding.rvProfile.adapter = ProfileAdapter(profileData)

        return binding.root
    }
}