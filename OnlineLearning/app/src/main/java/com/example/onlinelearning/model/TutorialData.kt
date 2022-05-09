package com.example.onlinelearning.model

import com.example.onlinelearning.R

data class TutorialData(
    val image: Int,
    val header: String,
    val description: String
) {
    companion object {
        fun getTutorialData(): ArrayList<TutorialData> {
            val tutorialData: ArrayList<TutorialData> = arrayListOf()
            tutorialData.add(
                TutorialData(
                    R.drawable.ic_tutorial_first_image,
                    "Teaching",
                    "Our new service makes it easy for you to work anywhere, there are new features will ready help you."
                )
            )
            tutorialData.add(
                TutorialData(
                    R.drawable.ic_tutorial_second_image,
                    "Learning",
                    "Our new service makes it easy for you to work anywhere, there are new features will ready help you."
                )
            )
            tutorialData.add(
                TutorialData(
                    R.drawable.ic_tutorial_third_image,
                    "Examination",
                    "Our new service makes it easy for you to work anywhere, there are new features will ready help you."
                )
            )
            return tutorialData
        }
    }
}
