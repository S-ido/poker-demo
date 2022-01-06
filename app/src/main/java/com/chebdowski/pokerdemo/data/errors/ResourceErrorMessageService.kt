package com.chebdowski.pokerdemo.data.errors

import com.chebdowski.pokerdemo.R
import com.chebdowski.pokerdemo.domain.Failure

class ResourceErrorMessageService {

    fun getMessage(failure: Failure): Int =
        when (failure) {
            Failure.Http.RequestTimeout -> R.string.error_request_timeout
            Failure.Http.ServerError -> R.string.error_request_server
            Failure.Http.UnhandledError -> R.string.error_request_general
            Failure.Unknown -> R.string.error_unknown
        }
}