package com.example.mytmdb.domain.models

data class Film(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPreviewUrl: String,
    val posterFullSizeUrl: String,
    val voteAverage: Double
)