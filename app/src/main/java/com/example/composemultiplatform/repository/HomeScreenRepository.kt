package com.example.composemultiplatform.repository

import com.example.composemultiplatform.api.APIService
import com.example.composemultiplatform.model.HomeResultModel

interface HomeScreenRepository {
    suspend fun getHomeScreen(
    ): HomeResultModel
}

class HomeScreenRepositoryImpl(
    private val remoteDataSource: APIService
) : HomeScreenRepository {
    override suspend fun getHomeScreen(): HomeResultModel {
        return remoteDataSource.getHomeResults()
    }
}