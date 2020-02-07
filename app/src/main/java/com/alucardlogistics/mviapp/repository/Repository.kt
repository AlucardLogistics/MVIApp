package com.alucardlogistics.mviapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alucardlogistics.mviapp.api.MyRetrofitBuilder
import com.alucardlogistics.mviapp.ui.main.state.MainViewState
import com.alucardlogistics.mviapp.util.ApiEmptyResponse
import com.alucardlogistics.mviapp.util.ApiErrorResponse
import com.alucardlogistics.mviapp.util.ApiSuccessResponse
import com.alucardlogistics.mviapp.util.DataState

object Repository {
    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getBlogPost()) {apiResponse ->
            object: LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {

                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                data = MainViewState(
                                    blogPost = apiResponse.body
                                )
                            )
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(
                                message = apiResponse.errorMessage
                            )
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(
                                message = "HTTP 204. Nothing to Return! ¯\\_(ツ)_/¯"
                            )
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getUser(userId)) {apiResponse ->
            object: LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {

                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                data = MainViewState(
                                    user = apiResponse.body
                                )
                            )
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(
                                message = apiResponse.errorMessage
                            )
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(
                                message = "HTTP 204. Nothing to Return! ¯\\_(ツ)_/¯"
                            )
                        }
                    }
                }
            }
        }
    }
}