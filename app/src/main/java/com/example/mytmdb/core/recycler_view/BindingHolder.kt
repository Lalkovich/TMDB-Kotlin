package com.example.mytmdb.core.recycler_view

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BindingHolder<B : ViewBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)