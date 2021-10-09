package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.remote.dto.UserResponse

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserResponse> {
        val users = service.callUsers().execute()

        return users.body() ?: emptyList()
    }
}