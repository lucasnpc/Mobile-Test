package com.picpay.desafio.android.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UserResponse(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
) : Parcelable