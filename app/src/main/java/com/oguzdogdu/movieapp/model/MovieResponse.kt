package com.oguzdogdu.movieapp.model

data class MovieResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)