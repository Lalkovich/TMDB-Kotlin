package com.example.mytmdb.domain.repositories

import com.example.mytmdb.domain.models.Film
import io.reactivex.Single

interface FilmsRepository {
    fun getFilms(page:Int):Single<List<Film>>

    fun getFilm(id:Int):Single<Film>
}