package com.oguzdogdu.movieapp.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.oguzdogdu.movieapp.model.moviedetails.MovieDetails
import com.oguzdogdu.movieapp.repository.MovieDetailsRepository
import com.oguzdogdu.movieapp.service.MovieInterface
import com.oguzdogdu.movieapp.utils.Result
import com.oguzdogdu.movieapp.utils.Status
import com.oguzdogdu.movieapp.view.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface,
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Result<MovieDetails>>()
    val movieDetails: LiveData<Result<MovieDetails>> = _movieDetails

    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Result(Status.LOADING, null, null))
        _movieDetails.postValue(movieDetailsRepository.getMovieDetails(imdbId))
    }


}