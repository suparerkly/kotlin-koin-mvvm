package com.example.composemultiplatform.api

import com.example.composemultiplatform.model.HomeResultModel
import retrofit2.http.GET

interface APIService {
    @GET("84c0cdc7-63b9-4232-aa05-0a1399f0f1af")
    suspend fun getHomeResults(
    ): HomeResultModel
}
