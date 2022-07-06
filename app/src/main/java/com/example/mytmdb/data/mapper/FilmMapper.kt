package com.example.mytmdb.data.mapper

import com.example.mytmdb.data.mapper.base.Mapper
import com.example.mytmdb.data.response.FilmResponse
import com.example.mytmdb.domain.models.Film

internal class FilmMapper: Mapper<FilmResponse,Film> {
    override fun toDomain(data: FilmResponse): Film {
        return Film(
            id = data.id,
            originalTitle = data.originalTitle ?: "",
            overview = data.overview ?: "",
            posterPreviewUrl = posterPreviewUrlPart + data.posterPath,
            posterFullSizeUrl = posterFullSizeUrlPart + data.posterPath,
            voteAverage = data.voteAverage ?: 0.0
        )
    }
    private companion object {
        const val posterPreviewUrlPart = "https://image.tmdb.org/t/p/w500"
        const val posterFullSizeUrlPart = "https://image.tmdb.org/t/p/original"
    }
}