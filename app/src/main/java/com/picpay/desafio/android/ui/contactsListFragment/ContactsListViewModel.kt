package com.picpay.desafio.android.ui.contactsListFragment

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.remote.dto.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(private val repository: PicPayRepository) :
    ViewModel() {
    suspend fun getUsers(): ArrayList<UserResponse> = repository.getUsersRemote()
}