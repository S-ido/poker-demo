package com.chebdowski.pokerdemo.di

import com.chebdowski.pokerdemo.data.ReplayPokerApi
import com.chebdowski.pokerdemo.data.ReplayPokerRepository
import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import com.chebdowski.pokerdemo.presentation.ringgames.RingGamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { providePokerRepository(get()) }
//    factory { GlobalScope.launch { provideGetRingsUseCase(get()) } }
    factory { provideGetRingsUseCase(get()) }
    viewModel { provideRingGamesViewModel(get()) }
}

fun providePokerRepository(api: ReplayPokerApi): PokerRepository = ReplayPokerRepository(api)
fun provideGetRingsUseCase(pokerRepository: PokerRepository) = GetRingsUseCase(pokerRepository)
//suspend fun provideGetRingsUseCase(pokerRepository: PokerRepository): GetRingsUseCase = getRings(pokerRepository)

fun provideRingGamesViewModel(getRingsUseCase: GetRingsUseCase) = RingGamesViewModel(getRingsUseCase)