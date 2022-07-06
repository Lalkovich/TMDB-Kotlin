package com.example.mytmdb.di

import com.example.mytmdb.data.mapper.FilmMapper
import com.example.mytmdb.data.mapper.base.Mapper
import com.example.mytmdb.data.repository.FilmRepositoryImpl
import com.example.mytmdb.data.response.FilmResponse
import com.example.mytmdb.domain.models.Film
import com.example.mytmdb.domain.repositories.FilmsRepository
import com.example.mytmdb.presentation.film.film_details.FilmDetailsViewModel
import com.example.mytmdb.presentation.film.film_list.FilmListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allModule = module{

    single<Mapper<FilmResponse, Film>>{
        FilmMapper()
    }

    single<FilmsRepository>{FilmRepositoryImpl(get(),get())}
    viewModel{FilmListViewModel(get())}
    viewModel{FilmDetailsViewModel(get())}
}