package com.example.mytmdb.presentation.film.film_list.adapter

import com.example.mytmdb.domain.models.Film

data class FilmCell(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val voteAverage: String
) {
    companion object {
        fun from(domain: Film): FilmCell {
            return FilmCell(
                id = domain.id,
                title = domain.originalTitle,
                overview = domain.overview,
                posterUrl = domain.posterPreviewUrl,
                voteAverage = domain.voteAverage.toString()
            )
        }
    }
}