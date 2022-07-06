package com.example.mytmdb.presentation.film.film_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytmdb.core.view_model.BaseViewModel
import com.example.mytmdb.domain.models.Film
import com.example.mytmdb.domain.repositories.FilmsRepository

class FilmDetailsViewModel(
    private val filmsRepository: FilmsRepository,
): BaseViewModel() {

    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film> = _film

    fun getFilm(id:String){
        filmsRepository.getFilm(id.toInt()).await {
            _film.postValue(it)
        }
    }
}