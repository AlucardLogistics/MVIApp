package com.alucardlogistics.mviapp.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alucardlogistics.mviapp.R
import com.alucardlogistics.mviapp.ui.main.state.MainStateEvent
import com.alucardlogistics.mviapp.ui.main.state.MainStateEvent.*
import java.lang.Exception

class MainFragment: Fragment() {

    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: DataState: $dataState")

            //handle the data <T>
            dataState.data?.let { mainViewState ->
                mainViewState.blogPost?.let {blogPosts ->
                    //set blog posts data
                    viewModel.setBlogListData(blogPosts)
                }
                mainViewState.user?.let {user ->
                    // set user data
                    viewModel.setUser(user)
                }
            }

            //handle errors
            dataState.message?.let {

            }

            //handle loading
            dataState.loading.let {
                
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPost?.let {
                println("DEBUG: Setting blog post to RecyclerView: $it")
            }

            viewState.user?.let {
                println("DEBUG: Setting user data: $it")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_get_user -> triggerGetUserEvent()
            R.id.action_get_blogs -> triggerGetBlogsEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        // server has just 1 user where userId is always "1"
        viewModel.setStateEvent(GetUserEvent("1"))
    }
}