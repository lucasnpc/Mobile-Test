package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.remote.PicPayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePicPayRepository(service: PicPayService): PicPayRepository =
        PicPayRepository(service = service)
}