package com.example.mytmdb.presentation.film.film_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytmdb.R
import com.example.mytmdb.core.fragment.BaseVMFragment
import com.example.mytmdb.core.nonNullObserve
import com.example.mytmdb.databinding.FragmentFilmListBinding
import com.example.mytmdb.presentation.film.film_details.FilmDetailsFragment
import com.example.mytmdb.presentation.film.film_list.adapter.FilmsAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

class FilmListFragment: BaseVMFragment<FragmentFilmListBinding,FilmListViewModel>(R.layout.fragment_film_list) {

    override val binding: FragmentFilmListBinding by viewBinding()

    override val viewModel: FilmListViewModel by viewModel()

    private val adapter:FilmsAdapter by lazy { FilmsAdapter() }


    override fun initView() {
        binding.rvFilmList.adapter = adapter
    }

    override fun initListeners() {
        adapter.setOnClickListener { findNavController().navigate(R.id.action_filmListFragment_to_filmDetailsFragment,
            bundleOf("filmId" to it)) }
    }

    override fun initObservers(){
        viewModel.films.nonNullObserve(viewLifecycleOwner,adapter::submitList)
    }

}