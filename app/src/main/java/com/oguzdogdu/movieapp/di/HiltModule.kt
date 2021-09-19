package com.oguzdogdu.movieapp.di

import com.oguzdogdu.movieapp.repository.MovieDetailsRepository
import com.oguzdogdu.movieapp.utils.Constants.BASE_URL
import com.oguzdogdu.movieapp.service.MovieInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideRetrofit(): MovieInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(movieInterface: MovieInterface): MovieDetailsRepository {
        return MovieDetailsRepository((movieInterface))
    }
}