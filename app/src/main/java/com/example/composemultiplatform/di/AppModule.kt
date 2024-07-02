package com.example.composemultiplatform.di

import com.example.composemultiplatform.api.APIService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideConverterFactory(): MoshiConverterFactory =
    MoshiConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
}

fun provideService(retrofit: Retrofit): APIService =
    retrofit.create(APIService::class.java)

val appModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(),get()) }
    single { provideService(get()) }
}