package com.example.android_assignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.android_assignment.R
import com.example.android_assignment.databinding.ActivityMainBinding
import com.example.android_assignment.utils.Constants
import com.example.android_assignment.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btMostEmailed.setOnClickListener {
            moveToNextScreen(Constants.NewsType.EMAILED)
        }

        binding.btMostViewed.setOnClickListener {
            moveToNextScreen(Constants.NewsType.VIEWED)

        }

        binding.btMostShared.setOnClickListener {
            moveToNextScreen(Constants.NewsType.SHARED)
        }

    }

    private fun moveToNextScreen(type : Constants.NewsType) {
        val intent = Intent(this, NewsActivity::class.java)
        intent.putExtra("newType", type.name)
        startActivity(intent)
    }
}