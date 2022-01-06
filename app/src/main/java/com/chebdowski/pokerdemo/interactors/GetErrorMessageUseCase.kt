package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.Failure

interface GetErrorMessageUseCase {

    operator fun invoke(failure: Failure): Int
}