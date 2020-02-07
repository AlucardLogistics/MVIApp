package com.alucardlogistics.mviapp.api

import androidx.lifecycle.LiveData
import com.alucardlogistics.mviapp.model.BlogPost
import com.alucardlogistics.mviapp.model.User
import com.alucardlogistics.mviapp.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

    @GET("placeholder/blogs")
    fun getBlogPost(): LiveData<GenericApiResponse<List<BlogPost>>>

}