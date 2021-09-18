package com.oguzdogdu.movieapp.service

import com.oguzdogdu.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Response<MovieResponse>
}