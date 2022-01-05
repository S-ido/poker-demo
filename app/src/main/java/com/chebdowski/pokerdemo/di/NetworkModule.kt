package com.chebdowski.pokerdemo.di

import com.chebdowski.pokerdemo.BuildConfig
import com.chebdowski.pokerdemo.data.ReplayPokerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideReplayPokerApi(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    return builder.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.REPLAY_POKER_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

fun provideReplayPokerApi(retrofit: Retrofit): ReplayPokerApi = retrofit.create(ReplayPokerApi::class.java)