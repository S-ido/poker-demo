package com.chebdowski.pokerdemo.domain

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val failure: Failure) : Result<Nothing>()

    inline fun <C> fold(success: (T) -> C, error: (Failure) -> C): C = when (this) {
        is Success -> success(data)
        is Error -> error(failure)
    }
}