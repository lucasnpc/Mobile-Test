package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.dto.UserResponse


interface PicPayService {

    suspend fun getUsers(): ArrayList<UserResponse>

}