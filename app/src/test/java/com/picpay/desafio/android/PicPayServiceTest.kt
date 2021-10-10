package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.remote.PicPayServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

class PicPayServiceTest {

    private val service = PicPayServiceImpl(
        client = HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    )

    private val repository = PicPayRepository(service)

    @Test
    fun getUsersTest() {
        runBlocking {
            val users = repository.getUsersRemote()
            users.forEach {
                println(it)
            }
            assert(users.isNotEmpty())
        }
    }
}