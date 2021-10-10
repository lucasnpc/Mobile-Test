package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.remote.PicPayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePicPayRepository(service: PicPayService): PicPayRepository =
        PicPayRepository(service = service)
}