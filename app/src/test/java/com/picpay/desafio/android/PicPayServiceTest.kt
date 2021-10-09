package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.PicPayService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayServiceTest {

    @Test
    fun getUsersTest() {
        runBlocking {
            val mockService = PicPayService.create()
            val users = mockService.getUsers()
            println(users)
            assert(users != null)
        }
    }
}