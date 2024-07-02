package com.example.composemultiplatform.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemultiplatform.domain.usecase.LoadHomeScreenUseCase
import com.example.composemultiplatform.model.HomeResultModel
import com.example.composemultiplatform.model.LayoutItem
import com.example.composemultiplatform.utils.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val homeScreenUseCase: LoadHomeScreenUseCase
) : ViewModel() {

    private val _homeTestUiState = MutableStateFlow(HomeResultModel())
    val homeTestUiState: StateFlow<HomeResultModel> get() = _homeTestUiState

    private val _homeBannerUiState = MutableStateFlow(LayoutItem())
    val homeBannerUiState: StateFlow<LayoutItem> get() = _homeBannerUiState

    init {
        getHomeScreen()
    }

    fun getHomeScreen() {
        viewModelScope.launch {
            try {
                val result = homeScreenUseCase.execute()
                when (result) {
                    is UseCaseResult.Success -> {
                        val banner = result.data?.data?.layout?.filter {
                            it.type == "banner"
                        }
                        banner?.let { data ->
                            _homeBannerUiState.update {
                                data[0]
                            }
                        }
                    }

                    is UseCaseResult.Error -> {

                    }

                    else -> {

                    }
                }
            } catch (ex: Exception) {
                Log.d("exception", "ERROR EXCEPTION")
            }
        }
    }
}