package com.leomarkpaway.retrofit

import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.leomarkpaway.retrofit.adapter.PostAdapter
import com.leomarkpaway.retrofit.common.base.BaseActivity
import com.leomarkpaway.retrofit.databinding.ActivityMainBinding
import com.leomarkpaway.retrofit.model.AllPost

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        setSupportActionBar(binding.toolbar)
        viewModel.getAllPost()
    }

    override fun subscribe() {
        observeAllPost()
    }

    private fun setupPostList(posts: AllPost) = with(binding.rvPost) {
        adapter = PostAdapter(posts) {/*TODO on click post item*/}
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeAllPost() {
        viewModel.allPost.observe(this) { posts ->
            setupPostList(posts)
        }
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