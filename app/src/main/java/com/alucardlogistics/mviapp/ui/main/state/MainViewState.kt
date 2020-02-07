package com.alucardlogistics.mviapp.ui.main.state

import com.alucardlogistics.mviapp.model.BlogPost
import com.alucardlogistics.mviapp.model.User

data class MainViewState(
    var blogPost: List<BlogPost>? = null,
    var user: User? = null
)