package com.example.mytmdb.data.network

import com.example.mytmdb.data.response.FilmResponse
import com.example.mytmdb.data.response.ListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {
    @GET("discover/movie")
    fun getFilms(
        @Query("page")page:Int,
        @Query("api_key")api_key:String = API_KEY
    ):Single<ListResponse<FilmResponse>>

    @GET("movie/{id}")
    fun getFilm(
        @Path("id")id:Int,
        @Query("api_key")api_key:String = API_KEY
    ):Single<FilmResponse>

    companion object{
       const val API_KEY = "d86412600ac9f3c72d6fe1e52afa68e5"
    }
}