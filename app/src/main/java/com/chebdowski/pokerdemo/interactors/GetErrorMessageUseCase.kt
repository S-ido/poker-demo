package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.ErrorMessageRepository
import com.chebdowski.pokerdemo.domain.Failure

class GetErrorMessageUseCase(private val errorMessageRepository: ErrorMessageRepository) {

    operator fun invoke(failure: Failure) = errorMessageRepository.getMessage(failure)
}