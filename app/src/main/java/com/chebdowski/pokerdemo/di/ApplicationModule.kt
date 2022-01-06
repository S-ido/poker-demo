package com.chebdowski.pokerdemo.di

import com.chebdowski.pokerdemo.data.ReplayPokerApi
import com.chebdowski.pokerdemo.data.ReplayPokerRepository
import com.chebdowski.pokerdemo.data.errors.ResourceErrorMessageService
import com.chebdowski.pokerdemo.data.errors.ResourcesErrorMessageRepository
import com.chebdowski.pokerdemo.domain.ErrorMessageRepository
import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.interactors.GetErrorMessageUseCase
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import com.chebdowski.pokerdemo.presentation.ErrorMessageViewModel
import com.chebdowski.pokerdemo.presentation.ringgames.RingGamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { provideErrorMessageRepository() }
    single { providePokerRepository(get()) }
    factory { provideGetErrorMessageUseCase(get()) }
    factory { provideGetRingsUseCase(get()) }
    viewModel { provideErrorMessageViewModel(get()) }
    viewModel { provideRingGamesViewModel(get()) }
}

fun provideErrorMessageRepository(): ErrorMessageRepository = ResourcesErrorMessageRepository(ResourceErrorMessageService())
fun providePokerRepository(api: ReplayPokerApi): PokerRepository = ReplayPokerRepository(api)

fun provideGetErrorMessageUseCase(errorMessageRepository: ErrorMessageRepository) = GetErrorMessageUseCase(errorMessageRepository)
fun provideGetRingsUseCase(pokerRepository: PokerRepository) = GetRingsUseCase(pokerRepository)

fun provideErrorMessageViewModel(getErrorMessageUseCase: GetErrorMessageUseCase) = ErrorMessageViewModel(getErrorMessageUseCase)
fun provideRingGamesViewModel(getRingsUseCase: GetRingsUseCase) = RingGamesViewModel(getRingsUseCase)