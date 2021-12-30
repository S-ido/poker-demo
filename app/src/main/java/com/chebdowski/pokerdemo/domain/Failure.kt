package com.chebdowski.pokerdemo.domain

sealed class Failure {

    object Unknown : Failure()
}