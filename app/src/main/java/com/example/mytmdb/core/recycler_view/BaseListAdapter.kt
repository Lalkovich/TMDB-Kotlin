package com.example.mytmdb.core.recycler_view

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T, Binding : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BindingHolder<Binding>>(diffCallback) {

    private var _context: Context? = null
    protected val context: Context
        get() = _context!!

    private var onItemClickListener: ((T) -> Unit)? = null

    var isClickEnabled: Boolean = false
        set(value) {
            field = value
            recyclerView?.isClickable = field
            recyclerView?.isEnabled = field
        }

    var recyclerView: RecyclerView? = null

    final override fun onBindViewHolder(holder: BindingHolder<Binding>, position: Int) {
        val currentItem = getItem(holder.absoluteAdapterPosition)

        holder.binding.root.setOnClickListener {
            onClick(currentItem)
        }

        onBind(binding = holder.binding, currentItem = currentItem)
    }

    override fun submitList(list: List<T>?, commitCallback: Runnable?) {
        isClickEnabled = false
        val callback = Runnable {
            isClickEnabled = true
            commitCallback?.run()
        }
        super.submitList(list, callback)
    }

    override fun submitList(list: List<T>?) {
        submitList(list, null)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        _context = recyclerView.context
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        _context = null
        this.recyclerView = null
    }

    protected open fun onClick(currentItem: T) {
        onItemClickListener?.invoke(currentItem)
    }

    /**
     * Set onClickListener on ViewHolder
     * @param listener - lambda expression
     */
    fun setOnItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
        notifyDataSetChanged()
    }

    /**
     * Bind current item to current ViewHolder
     * @param binding - binding of current ViewHolder
     * @param currentItem - current item by holder.adapterPosition
     */
    abstract fun onBind(binding: Binding, currentItem: T)
}