package com.picpay.desafio.android.ui.contactsListFragment

data class ContactsListState(
    val isLoading: Boolean = false,
    val failed: Boolean = false,
    val success: Boolean = false
)
