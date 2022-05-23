package com.example.onlinelearning.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinelearning.R
import com.example.onlinelearning.adapter.TutorialAdapter
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.SharedPreference
import com.example.onlinelearning.databinding.ActivityTutorialBinding
import com.example.onlinelearning.model.TutorialData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding
    private lateinit var tutorialData: ArrayList<TutorialData>
    private var currentPosition = Constants.ZERO.toInt()
    private var currentProgress = Constants.ZERO
    private lateinit var tutorialSharedPreferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tutorialSharedPreferences = SharedPreference(getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE))
        initializeTutorialData()
        val adapter = TutorialAdapter(tutorialData)

        with(binding) {
            viewPager.adapter = adapter
            updateProgressBar(true)

            imgNext.setOnClickListener {
                if (currentPosition != tutorialData.count() - Constants.ONE) {
                    updateProgressBar(true)
                    currentPosition++
                    viewPager.setCurrentItem(currentPosition, true)
                    btnSkip.text = if (currentPosition == tutorialData.count() - Constants.ONE) getString(
                        R.string.get_started
                    ) else getString(R.string.skip)
                } else {
                    startAuthenticationActivity()
                }
            }

            btnSkip.setOnClickListener {
                startAuthenticationActivity()
            }

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position != currentPosition) {
                        updateProgressBar(position > currentPosition)
                    }
                    currentPosition = position
                    btnSkip.text = if (currentPosition == tutorialData.count() - Constants.ONE) getString(
                        R.string.get_started
                    ) else getString(R.string.skip)
                }
            })

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun updateProgressBar(isIncremented: Boolean) {
        val progress = ceil(Constants.HUNDRED.toDouble() / tutorialData.count())
        currentProgress += if (isIncremented) progress else (Constants.MINUS_ONE * progress)
        setAnimatedProgress(currentProgress.toInt())
    }

    private fun setAnimatedProgress(progress: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            ObjectAnimator.ofInt(binding.progressBar, getString(R.string.progress), binding.progressBar.progress, progress).setDuration(
                Constants.ONE_THOUSAND.toLong()).start()
            delay(Constants.ONE_THOUSAND.toLong())
        }
    }

    private fun startAuthenticationActivity() {
        tutorialSharedPreferences.setTutorialCompleted()
        startActivity(Intent(this@TutorialActivity, AuthenticationActivity::class.java))
        finish()
    }

    private fun initializeTutorialData() {
        tutorialData = arrayListOf()
        tutorialData = TutorialData.getTutorialData()
    }
}