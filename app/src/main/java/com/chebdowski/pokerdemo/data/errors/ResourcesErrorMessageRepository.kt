package com.chebdowski.pokerdemo.data.errors

import com.chebdowski.pokerdemo.domain.ErrorMessageRepository
import com.chebdowski.pokerdemo.domain.Failure

class ResourcesErrorMessageRepository(private val errorMessageService: ResourceErrorMessageService) : ErrorMessageRepository {

    override fun getMessage(failure: Failure) = errorMessageService.getMessage(failure)
}