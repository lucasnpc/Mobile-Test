package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.dto.UserResponse
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserResponse>>
}