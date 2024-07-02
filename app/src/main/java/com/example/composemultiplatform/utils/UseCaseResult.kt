package com.example.composemultiplatform.utils

sealed class UseCaseResult<out T : Any?> {
    class Success<out T : Any>(val data: T?) : UseCaseResult<T>()
    data object Complete : UseCaseResult<Nothing>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}