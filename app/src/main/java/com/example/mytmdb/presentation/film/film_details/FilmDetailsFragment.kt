package com.example.mytmdb.presentation.film.film_details

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytmdb.R
import com.example.mytmdb.core.fragment.BaseVMFragment
import com.example.mytmdb.core.load
import com.example.mytmdb.core.nonNullObserve
import com.example.mytmdb.databinding.FragmentFilmDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmDetailsFragment: BaseVMFragment<FragmentFilmDetailsBinding,FilmDetailsViewModel>(R.layout.fragment_film_details) {

    override val binding: FragmentFilmDetailsBinding by viewBinding()

    override val viewModel:FilmDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFilm(arguments?.getString("filmId")?: "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
    }

    private fun initObserves(){
        viewModel.film.nonNullObserve(viewLifecycleOwner){
            binding.overview.text = it.overview
            binding.filmName.text = it.originalTitle
            binding.poster.load(it.posterFullSizeUrl,it.posterPreviewUrl)
        }
    }

}