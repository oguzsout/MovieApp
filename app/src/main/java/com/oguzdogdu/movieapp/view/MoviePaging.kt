package com.oguzdogdu.movieapp.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzdogdu.movieapp.utils.Constants
import com.oguzdogdu.movieapp.model.Search
import com.oguzdogdu.movieapp.service.MovieInterface

class MoviePaging(private val s: String, private val movieInterface: MovieInterface) :
    PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        val page = params.key ?: 1

        return try {
            val data = movieInterface.getAllMovies(s, page, Constants.API_KEY)
            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.Search?.isEmpty()!!) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}