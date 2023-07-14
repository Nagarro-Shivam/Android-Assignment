package com.example.android_assignment.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_assignment.adapter.NewsAdapter
import com.example.android_assignment.databinding.ActivityNewsBinding
import com.example.android_assignment.utils.Constants
import com.example.android_assignment.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding :ActivityNewsBinding
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsType = intent.getStringExtra("newType") ?: return

        val newAdapter = NewsAdapter {
            //onClick
            Toast.makeText(this, "Clicked ${it.id}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            adapter = newAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                when (newsType) {
                    Constants.NewsType.EMAILED.name ->

                        viewModel.emailedNews.collect {
                            val result = it ?: return@collect

                            binding.swipeRefreshLayout.isRefreshing = result is NetworkResult.Loading
                            binding.recyclerView.isVisible = !result.data.isNullOrEmpty()
                            binding.tvErrorText.text = result.message ?: ""
                            binding.tvErrorText.isVisible = result.message != null && result.data.isNullOrEmpty()
                            newAdapter.submitList(result.data)

                        }
                    Constants.NewsType.SHARED.name ->

                        viewModel.sharedNews.collect {
                            val result = it ?: return@collect

                            binding.swipeRefreshLayout.isRefreshing = result is NetworkResult.Loading
                            binding.recyclerView.isVisible = !result.data.isNullOrEmpty()
                            binding.tvErrorText.text = result.message ?: ""
                            binding.tvErrorText.isVisible = result.message != null && result.data.isNullOrEmpty()
                            newAdapter.submitList(result.data)
                        }

                    Constants.NewsType.VIEWED.name ->

                        viewModel.viewedNews.collect {
                            val result = it ?: return@collect

                            binding.swipeRefreshLayout.isRefreshing = result is NetworkResult.Loading
                            binding.recyclerView.isVisible = !result.data.isNullOrEmpty()
                            binding.tvErrorText.text = result.message ?: ""
                            binding.tvErrorText.isVisible = result.message != null && result.data.isNullOrEmpty()
                            newAdapter.submitList(result.data)
                        }
                }
            }
        }

    }
}