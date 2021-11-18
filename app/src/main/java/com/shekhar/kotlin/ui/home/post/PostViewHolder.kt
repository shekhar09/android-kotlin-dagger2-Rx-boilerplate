package com.shekhar.kotlin.ui.home.post

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.shekhar.kotlin.dagger.R
import com.shekhar.kotlin.di.component.ViewHolderComponent
import com.shekhar.kotlin.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostViewHolder(parent: ViewGroup):BaseItemViewHolder<Post,PostViewModel> (
        R.layout.item_view_post,parent){


    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }


    override fun setupView(view: View) {

    }


    override fun setupObserver() {
        super.setupObserver()
        viewModel.data.observe(this, Observer {
            itemView.tv_message.text = it.text
        })

        itemView.setOnClickListener {
            showMessage("$adapterPosition clicked")
        }
    }
}