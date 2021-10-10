package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.PicPayService
import javax.inject.Inject

class PicPayRepository @Inject constructor(private val service: PicPayService) {

    suspend fun getUsersRemote() = service.getUsers()
}