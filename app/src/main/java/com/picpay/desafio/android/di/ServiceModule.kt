package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.remote.PicPayServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(ActivityRetainedComponent::class)
object ServiceModule {
    @Provides
    fun providePicpayService(): PicPayService = PicPayServiceImpl(
        client = HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    )
}