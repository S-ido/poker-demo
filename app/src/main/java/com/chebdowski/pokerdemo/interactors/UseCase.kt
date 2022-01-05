package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.Result

interface UseCase<T> {
    suspend operator fun invoke(): Result<T>
}