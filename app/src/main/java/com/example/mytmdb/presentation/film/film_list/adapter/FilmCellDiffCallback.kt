package com.example.mytmdb.presentation.film.film_list.adapter

import androidx.recyclerview.widget.DiffUtil

class FilmCellDiffCallback:DiffUtil.ItemCallback<FilmCell>() {
    override fun areItemsTheSame(oldItem: FilmCell, newItem: FilmCell): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FilmCell, newItem: FilmCell): Boolean {
        return oldItem == newItem
    }
}