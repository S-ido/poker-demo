package com.chebdowski.pokerdemo.domain

interface ErrorMessageRepository {

    /**
     * Get an error message for a given [Failure] type.
     * @param failure representation of an error handled by the app
     * @return Integer value representing String resource id.
     */
    fun getMessage(failure: Failure): Int
}