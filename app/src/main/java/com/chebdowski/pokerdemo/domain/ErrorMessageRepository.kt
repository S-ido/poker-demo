package com.chebdowski.pokerdemo.domain

interface ErrorMessageRepository {

    fun getMessage(failure: Failure): Int
}