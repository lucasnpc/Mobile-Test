package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.dto.UserResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import retrofit2.Call

class PicPayServiceImpl(private val client: HttpClient) : PicPayService {

    override suspend fun getUsers(): ArrayList<UserResponse> =
        try {
            client.get { url(Routes.GET_USERS) }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            ArrayList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            ArrayList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            ArrayList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ArrayList()
        }
}