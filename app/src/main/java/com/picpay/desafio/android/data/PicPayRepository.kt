package com.picpay.desafio.android.data

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.ui.contactsListFragment.ContactsListState
import javax.inject.Inject

class PicPayRepository(private val service: PicPayService) {

    suspend fun getUsersRemote(state: MutableLiveData<ContactsListState>? = null) = service.getUsers().let {
        if (it.isEmpty())
            state?.value = ContactsListState(isLoading = false, failed = true)
        else
            state?.value = ContactsListState(isLoading = false, success = true)
        it
    }
}