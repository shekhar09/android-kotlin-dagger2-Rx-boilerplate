package com.shekhar.kotlin.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.shekhar.kotlin.MyApplication
import com.shekhar.kotlin.di.component.DaggerViewHolderComponent
import com.shekhar.kotlin.di.component.ViewHolderComponent
import com.shekhar.kotlin.di.module.ViewHolderModule
import com.shekhar.kotlin.utils.display.Toaster
import javax.inject.Inject

abstract class BaseItemViewHolder<T: Any, VM :BaseItemViewModel<T>>(
        @LayoutRes layoutId:Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId,parent,false)),
        LifecycleOwner{

    init {
        onCreate()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    open fun bind (data :T)
    {
        viewModel.updateData(data)
    }

    protected fun onCreate()
    {
        injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        setupObserver()
        setupView(itemView)
    }


    fun onStart()
    {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    fun onStop()
    {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    fun onDestroy()
    {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    private fun buildViewHolderComponent() =
            DaggerViewHolderComponent
                    .builder().applicationComponent((itemView.context!!.applicationContext as MyApplication).applicationComponent)
                    .viewHolderModule(ViewHolderModule(this))
                    .build()



    protected open fun setupObserver()
    {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    fun showMessage(message: String) = Toaster.show(itemView.context, message)

    fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected abstract fun setupView(view: View)

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)


}