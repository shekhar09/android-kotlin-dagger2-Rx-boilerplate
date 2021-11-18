package com.shekhar.kotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.shekhar.kotlin.MyApplication
import com.shekhar.kotlin.di.component.DaggerFragmentComponent
import com.shekhar.kotlin.di.component.FragmentComponent
import com.shekhar.kotlin.di.module.FragmentModule
import com.shekhar.kotlin.utils.display.Toaster
import javax.inject.Inject

abstract class BaseFragment <VM: BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel:VM


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObserver()
        viewModel.onCreate()



    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?=
            inflater.inflate(provideLayoutId(),container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
    }

    protected open fun setupObserver()
    {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }


    private fun buildFragmentComponent() =
            DaggerFragmentComponent
                    .builder().applicationComponent((context!!.applicationContext as MyApplication).applicationComponent)
                    .fragmentModule(FragmentModule(this))
                    .build()



    fun showMessage(message: String) = context?.let { Toaster.show(it, message) }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    @LayoutRes
    protected abstract fun provideLayoutId():Int

    protected abstract fun setupView(view: View)

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)


}