package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import retrofit2.Response
import java.net.SocketTimeoutException

fun <T, R> Response<T>.toResult(transform: (T) -> R): Result<R> =
    try {
        when (isSuccessful) {
            true -> {
                val body = body()
                if (body != null) {
                    Result.Success(transform(body))
                } else {
                    Result.Error(Failure.Http.ServerError)
                }
            }
            false -> Result.Error(getError(code()))
        }
    } catch (e: SocketTimeoutException) {
        Result.Error(Failure.Http.RequestTimeout)
    } catch (t: Throwable) {
        // TODO Send stack trace to a crash reporting tool
        Result.Error(Failure.Http.UnhandledError)
    }

private fun getError(code: Int): Failure =
    when (code) {
        408 -> Failure.Http.RequestTimeout
        in 500..599 -> Failure.Http.ServerError
        else -> Failure.Http.UnhandledError
    }