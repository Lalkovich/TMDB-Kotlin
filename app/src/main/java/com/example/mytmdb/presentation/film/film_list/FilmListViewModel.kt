package com.example.mytmdb.presentation.film.film_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytmdb.core.view_model.BaseViewModel
import com.example.mytmdb.domain.repositories.FilmsRepository
import com.example.mytmdb.presentation.film.film_list.adapter.FilmCell


class FilmListViewModel(
    private val filmsRepository: FilmsRepository,
): BaseViewModel() {
    private val _films = MutableLiveData<List<FilmCell>>()
    val films: LiveData<List<FilmCell>> = _films

    init {
        getFilms()
    }

    private fun getFilms(){
        filmsRepository.getFilms(1).await {films ->
            _films.postValue(films.map(FilmCell.Companion::from))
        }
    }
}