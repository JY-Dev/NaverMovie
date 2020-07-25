package com.jay.navermovie.api

import com.jay.navermovie.data.search.NaverResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String
    ): Call<NaverResult>

}