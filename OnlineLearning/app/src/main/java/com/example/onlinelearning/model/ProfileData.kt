package com.example.onlinelearning.model

import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants

data class ProfileData(
    val image: Int,
    val title: String,
    val viewType: Int
) {
    companion object {
        fun getProfileData(): ArrayList<ProfileData> {
            val profileData: ArrayList<ProfileData> = arrayListOf()

            profileData.add(ProfileData(R.drawable.ic_dark_mode, "Dark Mode", Constants.ZERO.toInt()))
            profileData.add(ProfileData(R.drawable.ic_settings, "Settings", Constants.ONE))
            profileData.add(ProfileData(R.drawable.ic_wallet, "Payment Details", Constants.ONE))
            profileData.add(ProfileData(R.drawable.ic_achievement, "Achievement", Constants.ONE))
            profileData.add(ProfileData(R.drawable.ic_heart, "Love", Constants.ONE))
            return profileData
        }
    }
}
