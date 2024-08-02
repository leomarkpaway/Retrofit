package com.leomarkpaway.retrofit

import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.leomarkpaway.retrofit.common.base.BaseActivity
import com.leomarkpaway.retrofit.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        setSupportActionBar(binding.toolbar)
    }

    override fun subscribe() {
        //TODO "Not yet implemented"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as (SearchView)
        searchView.queryHint = "Search post"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO "Filter item here"
                return true
            }
        })
        return true
    }

}