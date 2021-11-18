package com.shekhar.kotlin.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shekhar.kotlin.dagger.R
import com.shekhar.kotlin.di.component.FragmentComponent
import com.shekhar.kotlin.ui.base.BaseFragment
import com.shekhar.kotlin.ui.home.post.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {

        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var postAdapter: PostAdapter


    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun setupView(view: View) {
        recycler_post.apply {
            adapter = postAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun setupObserver() {
        super.setupObserver()

        viewModel.testData.observe(this, Observer {
            postAdapter.appendData(it)
        })

    }


}
