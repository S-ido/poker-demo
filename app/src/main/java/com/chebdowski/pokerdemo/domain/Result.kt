package com.chebdowski.pokerdemo.domain

sealed class Result<out T, out E> {

    data class Success<out T>(val data: T) : Result<T, Nothing>()

    data class Error<E>(val error: E) : Result<Nothing, E>()
}

typealias SafeResult<T> = Result<T, Failure>