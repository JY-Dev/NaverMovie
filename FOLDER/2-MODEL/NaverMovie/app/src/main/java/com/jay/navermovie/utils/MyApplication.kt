package com.jay.navermovie.utils

import android.app.Application
import com.jay.navermovie.api.NetworkService
import com.jay.navermovie.data.source.MovieRepository
import com.jay.navermovie.data.source.MovieRepositoryImpl
import com.jay.navermovie.data.source.database.MovieDao
import com.jay.navermovie.data.source.database.MovieDatabase
import com.jay.navermovie.data.source.local.MovieLocalDataSource
import com.jay.navermovie.data.source.local.MovieLocalDataSourceImpl
import com.jay.navermovie.data.source.remote.MovieRemoteDataSource
import com.jay.navermovie.data.source.remote.MovieRemoteDataSourceImpl

class MyApplication : Application() {

    private lateinit var networkManager: NetWorkManager
    private lateinit var api: NetworkService
    private lateinit var movieRemoeteDataSource: MovieRemoteDataSource
    private lateinit var movieLocalDataSource: MovieLocalDataSource
    private lateinit var movieDao: MovieDao
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        inject()
    }

    private fun inject() {
        networkManager = NetWorkManager(applicationContext)
        api = NetworkService
        movieDao = MovieDatabase.Factory.create(applicationContext).movieDao()
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
        movieRemoeteDataSource = MovieRemoteDataSourceImpl(api)
        movieRepository = MovieRepositoryImpl(movieRemoeteDataSource, movieLocalDataSource, networkManager)
    }
}