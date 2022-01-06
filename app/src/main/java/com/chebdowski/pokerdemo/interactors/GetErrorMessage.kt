package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.ErrorMessageRepository
import com.chebdowski.pokerdemo.domain.Failure

class GetErrorMessage(private val errorMessageRepository: ErrorMessageRepository) : GetErrorMessageUseCase {

    override fun invoke(failure: Failure) = errorMessageRepository.getMessage(failure)
}