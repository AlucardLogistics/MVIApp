package com.alucardlogistics.mviapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alucardlogistics.mviapp.model.BlogPost
import com.alucardlogistics.mviapp.model.User
import com.alucardlogistics.mviapp.ui.main.state.MainStateEvent
import com.alucardlogistics.mviapp.ui.main.state.MainStateEvent.*
import com.alucardlogistics.mviapp.ui.main.state.MainViewState
import com.alucardlogistics.mviapp.util.AbsentLiveData

class MainViewModel: ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when(stateEvent) {
            is GetBlogPostsEvent -> {
                return AbsentLiveData.create()
            }
            is GetUserEvent -> {
                return AbsentLiveData.create()
            }
            is None -> {
                return AbsentLiveData.create()
            }
        }
    }

    private fun getCurrentViewStateOrNew(): MainViewState {

        return viewState.value?.let {
            it
        }?: MainViewState()
    }

    fun setBlogListData(blogPost: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogPost = blogPost
        _viewState.value = update
    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }
}