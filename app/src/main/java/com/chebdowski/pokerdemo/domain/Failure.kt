package com.chebdowski.pokerdemo.domain

sealed class Failure {

    sealed class Http {
        object RequestTimeout : Failure()
        object ServerError : Failure()
        object UnhandledError : Failure()
    }

    object Unknown : Failure()
}