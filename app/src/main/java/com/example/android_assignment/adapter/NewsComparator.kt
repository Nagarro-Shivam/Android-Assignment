package com.example.android_assignment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.android_assignment.data.model.News

class NewsComparator :  DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
       return oldItem == newItem
    }

}