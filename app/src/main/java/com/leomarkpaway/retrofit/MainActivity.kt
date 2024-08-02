package com.leomarkpaway.retrofit

import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.leomarkpaway.retrofit.adapter.PostAdapter
import com.leomarkpaway.retrofit.common.base.BaseActivity
import com.leomarkpaway.retrofit.common.util.createIntent
import com.leomarkpaway.retrofit.databinding.ActivityMainBinding
import com.leomarkpaway.retrofit.model.AllPost
import com.leomarkpaway.retrofit.model.PostDetails
import com.leomarkpaway.retrofit.post_detail.PostDetailActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)
    lateinit var postAdapter: PostAdapter

    override fun setupViews() {
        setSupportActionBar(binding.toolbar)
        viewModel.getAllPost()
    }

    override fun subscribe() {
        observeAllPost()
    }

    private fun setupPostList(posts: AllPost) = with(binding.rvPost) {
        postAdapter = PostAdapter(posts) { itemPost -> onClickPostItem(itemPost) }
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
    }

    private fun onClickPostItem(postDetails: PostDetails) {
        val postDetailIntent = createIntent<PostDetailActivity> {
            putExtra("id", postDetails.id)
        }
       startActivity(postDetailIntent)
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
                postAdapter.filter.filter(newText)
                return true
            }
        })
        return true
    }

}