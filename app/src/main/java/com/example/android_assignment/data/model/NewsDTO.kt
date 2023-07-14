package com.example.android_assignment.data.model

import com.example.android_assignment.utils.Constants
import com.google.gson.annotations.SerializedName

data class NewsDTO(

    @SerializedName("abstract")
    val abs : String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any,
    val des_facet: List<String>,
    val eta_id: Int,
    val geo_facet: List<String>,
    val id: Long,
    val media: List<Media>,
    val nytdsection: String,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
) {

    fun toNews() : News = News(
        id = id,
        title = title,
        imageUrl =  "",
        description = abs,
        media = try {
            media[0].media_metadata[0].url
        }catch (e :  Exception) {
            Constants.FALLBACK_IMAGE
        }

    )
}