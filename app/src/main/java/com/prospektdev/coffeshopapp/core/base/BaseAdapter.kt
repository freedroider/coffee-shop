package com.prospektdev.coffeshopapp.core.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<M, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var clickListener: ItemClickListener<M>? = null
    private val items = mutableListOf<M>()

    override fun getItemCount(): Int = this.items.size

    protected fun inflate(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    fun add(item: M) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun add(items: List<M>) {
        val insertPosition = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(insertPosition, items.size)
    }

    @Suppress("unused")
    fun replace(position: Int, item: M) {
        this.items[position] = item
        notifyItemInserted(position)
    }

    @Suppress("unused")
    fun replace(items: List<M>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): M = this.items[position]

    fun getItems(): List<M> = this.items

    interface ItemClickListener<M> {
        fun onItemClick(item: M)
    }
}