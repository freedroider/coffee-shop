package com.prospektdev.coffeshopapp.feature.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.prospektdev.coffeshopapp.R
import com.prospektdev.coffeshopapp.core.base.BaseAdapter
import com.prospektdev.coffeshopapp.model.net.data.Place
import kotlinx.android.synthetic.main.item_places.view.*

class PlacesAdapter : BaseAdapter<Place, PlacesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.item_places))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener({ clickListener?.onItemClick(item) })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeNameTV: TextView = view.placeNameTV
        val placeAddressTV: TextView = view.placeAddressTV

        fun bind(item: Place) {
            placeNameTV.text = item.name
            placeAddressTV.text = item.address
        }
    }
}