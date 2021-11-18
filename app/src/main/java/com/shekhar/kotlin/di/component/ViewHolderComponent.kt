package com.shekhar.kotlin.di.component

import com.shekhar.kotlin.di.ViewHolderScope
import com.shekhar.kotlin.di.module.ViewHolderModule
import com.shekhar.kotlin.ui.home.post.PostViewHolder
import dagger.Component


@ViewHolderScope
@Component(dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class])
interface ViewHolderComponent {

    fun inject(viewHolder: PostViewHolder)
}