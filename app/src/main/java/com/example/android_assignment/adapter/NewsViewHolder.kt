package com.example.android_assignment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_assignment.data.model.News
import com.example.android_assignment.databinding.ItemNewsBinding

class NewsViewHolder(

    private val binding : ItemNewsBinding,
    private val onItemClick : (Int) -> Unit,

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.apply {

            Glide.with(itemView)
                .load(news.media)
                .into(ivImage)

            tvTitle.text = news.title

        }
    }

    init {

        binding.apply {
            root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
        }
    }
}