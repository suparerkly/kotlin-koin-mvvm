package com.example.composemultiplatform.di

import com.example.composemultiplatform.domain.usecase.LoadHomeScreenUseCase
import com.example.composemultiplatform.domain.usecase.LoadHomeScreenUseCaseImpl
import com.example.composemultiplatform.repository.HomeScreenRepository
import com.example.composemultiplatform.repository.HomeScreenRepositoryImpl
import com.example.composemultiplatform.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val HomeModule = module {
    factory<HomeScreenRepository> {
        HomeScreenRepositoryImpl(
            remoteDataSource = get()
        )
    }

    factory<LoadHomeScreenUseCase> {
        LoadHomeScreenUseCaseImpl(
            homeScreenRepository = get()
        )
    }

    viewModel { HomeScreenViewModel(homeScreenUseCase = get()) }
    viewModelOf(::HomeScreenViewModel)
}