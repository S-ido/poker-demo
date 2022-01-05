package com.chebdowski.pokerdemo.domain

sealed class Failure {

    object BadRequest : Failure()
    object Unauthorized : Failure()
    object Forbidden : Failure()
    object NotFound : Failure()
    object RequestTimeout : Failure()
    object ServerError : Failure()
    object UnhandledError : Failure()
    object RequestError : Failure()
    object NoData : Failure()
    object Unknown : Failure()
}