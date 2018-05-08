package com.prospektdev.coffeshopapp.feature.list

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import com.prospektdev.coffeshopapp.R
import com.prospektdev.coffeshopapp.core.base.BaseActivity
import com.prospektdev.coffeshopapp.core.base.BaseAdapter
import com.prospektdev.coffeshopapp.model.net.data.Place
import com.prospektdev.coffeshopapp.util.Starter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity(), BaseAdapter.ItemClickListener<Place>, Observer<List<Place>> {
    private lateinit var listViewModel: ListViewModel
    private lateinit var adapter: PlacesAdapter

    override fun onLocationEnabled() {
        setUpRecyclerView()
        listViewModel = provideViewModel(ListViewModel::class.java)
        listViewModel.observeList(this, this)
        listViewModel.observeLocation(this)
    }

    override fun obtainLayoutId(): Int = R.layout.activity_list

    override fun onItemClick(item: Place) {
        Starter.startMapActivity(this, item)
    }

    override fun onChanged(list: List<Place>?) {
        if (list != null) {
            progressBar.visibility = GONE
            adapter.replace(list)
        }
    }

    private fun setUpRecyclerView() {
        adapter = PlacesAdapter()
        adapter.clickListener = this
        placeRV.adapter = adapter
        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        placeRV.layoutManager = lm
    }
}