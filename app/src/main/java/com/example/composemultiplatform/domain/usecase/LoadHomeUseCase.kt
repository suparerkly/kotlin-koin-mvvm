package com.example.composemultiplatform.domain.usecase

import com.example.composemultiplatform.model.HomeResultModel
import com.example.composemultiplatform.repository.HomeScreenRepository
import com.example.composemultiplatform.utils.UseCaseResult

interface LoadHomeScreenUseCase {
    suspend fun execute(
    ): UseCaseResult<HomeResultModel>
}

class LoadHomeScreenUseCaseImpl(
    private val homeScreenRepository: HomeScreenRepository
) : LoadHomeScreenUseCase {
    override suspend fun execute(
    ): UseCaseResult<HomeResultModel> {
        return try {
            val result = homeScreenRepository.getHomeScreen()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

}