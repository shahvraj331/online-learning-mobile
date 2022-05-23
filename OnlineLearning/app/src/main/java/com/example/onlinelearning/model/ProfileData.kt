package com.example.onlinelearning.model

import com.example.onlinelearning.R

data class ProfileData(
    val image: Int,
    val title: String,
    val viewType: Int
) {
    companion object {
        fun getProfileData(): ArrayList<ProfileData> {
            val profileData: ArrayList<ProfileData> = arrayListOf()

            profileData.add(ProfileData(R.drawable.ic_dark_mode, "Dark Mode", 0))
            profileData.add(ProfileData(R.drawable.ic_settings, "Settings", 1))
            profileData.add(ProfileData(R.drawable.ic_wallet, "Payment Details", 1))
            profileData.add(ProfileData(R.drawable.ic_achievement, "Achievement", 1))
            profileData.add(ProfileData(R.drawable.ic_heart, "Love", 1))
            return profileData
        }
    }
}
