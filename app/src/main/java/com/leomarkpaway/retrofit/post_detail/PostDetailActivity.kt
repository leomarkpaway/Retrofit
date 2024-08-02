package com.leomarkpaway.retrofit.post_detail

import androidx.activity.viewModels
import com.leomarkpaway.retrofit.common.base.BaseActivity
import com.leomarkpaway.retrofit.databinding.ActivityPostDetailBinding
import com.leomarkpaway.retrofit.model.PostDetails

class PostDetailActivity : BaseActivity<PostDetailViewModel, ActivityPostDetailBinding>() {
    override val viewModel: PostDetailViewModel by viewModels()
    override fun inflateBinding() = ActivityPostDetailBinding.inflate(layoutInflater)

    override fun setupViews() {
        setActionbarTitle()
        val id = intent.getIntExtra("id", -0)
        viewModel.getPostDetailById(id)
    }

    override fun subscribe() {
        viewModel.postDetail.observe(this) {
            setupPostDetailContent(it)
        }
    }

    private fun setupPostDetailContent(postDetail: PostDetails) = with(binding) {
        tvTitle.text = postDetail.title
        tvBody.text = postDetail.body
    }

    private fun setActionbarTitle() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Post Detail"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}