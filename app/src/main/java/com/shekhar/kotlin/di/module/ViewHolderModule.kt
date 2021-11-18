package com.shekhar.kotlin.di.module

import androidx.lifecycle.LifecycleRegistry
import com.shekhar.kotlin.di.ViewHolderScope
import com.shekhar.kotlin.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*,*>) {

    @Provides
    @ViewHolderScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)

}
