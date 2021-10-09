package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.dto.UserResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun callUsers(): Call<List<UserResponse>>

    suspend fun getUsers(): ArrayList<UserResponse>?

    companion object {
        fun create(): PicPayService = PicPayServiceImpl(
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
}