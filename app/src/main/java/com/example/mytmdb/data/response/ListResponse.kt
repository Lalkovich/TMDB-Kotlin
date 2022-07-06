package com.example.mytmdb.data.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @SerializedName("page")
    val page: Int? = 0,

    @SerializedName("results")
    val results: List<T>? = listOf(),

    @SerializedName("total_results")
    val totalResults: Int? = 0,

    @SerializedName("total_pages")
    val totalPages: Int? = 0
)
