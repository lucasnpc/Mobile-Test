package com.picpay.desafio.android.ui.contactsListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.remote.dto.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(private val repository: PicPayRepository) :
    ViewModel() {

    private val _state = MutableLiveData<ContactsListState>()
    val state: LiveData<ContactsListState> = _state

    suspend fun getUsers(): ArrayList<UserResponse> {
        _state.value = ContactsListState(isLoading = true)
        return repository.getUsersRemote(_state)
    }

}