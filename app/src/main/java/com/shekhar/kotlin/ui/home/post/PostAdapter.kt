package com.shekhar.kotlin.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.shekhar.kotlin.ui.base.BaseAdapter

class PostAdapter(
        parentLifecycle:Lifecycle,
        postList: ArrayList<Post>
):BaseAdapter<Post,PostViewHolder>(parentLifecycle,postList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(parent)
}