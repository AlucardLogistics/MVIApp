package com.alucardlogistics.mviapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alucardlogistics.mviapp.api.MyRetrofitBuilder
import com.alucardlogistics.mviapp.model.BlogPost
import com.alucardlogistics.mviapp.ui.main.state.MainViewState
import com.alucardlogistics.mviapp.util.ApiEmptyResponse
import com.alucardlogistics.mviapp.util.ApiErrorResponse
import com.alucardlogistics.mviapp.util.ApiSuccessResponse

object Repositiry {
    fun getBlogPosts(): LiveData<MainViewState> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getBlogPost()) {apiResponse ->
            object: LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {

                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                blogPost = apiResponse.body
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState() //handle error
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState() //handle empty/error
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String): LiveData<MainViewState> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getUser(userId)) {apiResponse ->
            object: LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {

                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                user = apiResponse.body
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState() //handle error
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState() //handle empty/error
                        }
                    }
                }
            }
        }
    }
}