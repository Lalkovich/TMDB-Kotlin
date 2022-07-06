package com.example.mytmdb.presentation.film.film_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mytmdb.R
import com.example.mytmdb.core.load
import com.example.mytmdb.core.recycler_view.BaseListAdapter
import com.example.mytmdb.core.recycler_view.BaseRecyclerViewAdapter
import com.example.mytmdb.core.recycler_view.BindingHolder
import com.example.mytmdb.databinding.ItemListBinding

class FilmsAdapter: BaseListAdapter<FilmCell, ItemListBinding>(FilmCellDiffCallback()) {
    private var onClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: (String) -> Unit) {
        onClickListener = listener
    }

    override fun onBind(binding: ItemListBinding, currentItem: FilmCell) {
        binding.ivPoster.load(currentItem.posterUrl)
        binding.ivFilmName.text = currentItem.title
        binding.root.setOnClickListener {
            onClickListener?.invoke(currentItem.id.toString())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ItemListBinding> {
        return BindingHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}