package com.oguzdogdu.movieapp.repository

import com.oguzdogdu.movieapp.model.moviedetails.MovieDetails
import com.oguzdogdu.movieapp.service.MovieInterface
import com.oguzdogdu.movieapp.utils.Constants.API_KEY
import com.oguzdogdu.movieapp.utils.Result
import com.oguzdogdu.movieapp.utils.Status
import java.lang.Exception

class MovieDetailsRepository(private val movieInterface: MovieInterface) {


    suspend fun getMovieDetails(imdbId: String): Result<MovieDetails> {

        return try {

            val response = movieInterface.getMovieDetails(imdbId, API_KEY)
            if (response.isSuccessful) {
                Result(Status.SUCCESS, response.body(), "Success")
            } else {
                Result(Status.ERROR, null, null)
            }

        } catch (e: Exception) {
            Result(Status.ERROR, null, null)
        }
    }
}