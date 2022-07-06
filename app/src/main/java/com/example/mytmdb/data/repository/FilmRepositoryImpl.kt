package com.example.mytmdb.data.repository

import com.example.mytmdb.data.mapper.base.Mapper
import com.example.mytmdb.data.network.RemoteDataSource
import com.example.mytmdb.data.response.FilmResponse
import com.example.mytmdb.data.response.ListResponse
import com.example.mytmdb.domain.models.Film
import com.example.mytmdb.domain.repositories.FilmsRepository
import io.reactivex.Single

class FilmRepositoryImpl(
    private val remoteDataSource:RemoteDataSource,
    private val mapper:Mapper<FilmResponse,Film>
):FilmsRepository {
    override fun getFilms(page: Int): Single<List<Film>> {
        return remoteDataSource.getFilms(page)
            .map { listResponse -> listResponse.results?.map(mapper::toDomain) }
    }

    override fun getFilm(id: Int): Single<Film> {
        return remoteDataSource.getFilm(id)
            .map(mapper::toDomain)
    }

}