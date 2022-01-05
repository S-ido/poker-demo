package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import retrofit2.Response

fun <T, R> Response<T>.toResult(transform: (T) -> R): Result<R> =
    try {
        when (isSuccessful) {
            true -> {
                val body = body()
                if (body != null) {
                    Result.Success(transform(body))
                } else {
                    Result.Error(Failure.NoData)
                }
            }
            false -> Result.Error(getError(code()))
        }
    } catch (t: Throwable) {
        Result.Error(Failure.RequestError)
    }

private fun getError(code: Int): Failure =
    when (code) {
        400 -> Failure.BadRequest
        401 -> Failure.Unauthorized
        403 -> Failure.Forbidden
        404 -> Failure.NotFound
        408 -> Failure.RequestTimeout
        in 500..599 -> Failure.ServerError
        else -> Failure.UnhandledError
    }