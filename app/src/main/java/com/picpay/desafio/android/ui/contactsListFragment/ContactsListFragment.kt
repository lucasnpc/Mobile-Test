package com.picpay.desafio.android.ui.contactsListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.remote.dto.UserResponse
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class ContactsListFragment : Fragment(R.layout.contacts_list_fragment) {

    private val viewModel: ContactsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val users = produceState(initialValue = ArrayList<UserResponse>(), producer =
            {
                value = viewModel.getUsers()
            })
            ContactsList(users = users.value)
        }
    }

    @Preview
    @Composable
    fun ContactsList(users: ArrayList<UserResponse> = ArrayList()) {
        Scaffold(
            content = {
                LazyColumn(
                    modifier = Modifier
                        .background(colorResource(id = R.color.colorPrimaryDark))
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    item {
                        ContactsTitle()
                    }
                    items(users) {
                        ContactsItems(it)
                    }
                }

            }
        )
    }

    @Composable
    private fun ContactsItems(it: UserResponse) {
        Column(modifier = Modifier.fillMaxWidth(), content = {
            Row(content = {
                Image(
                    painter = rememberImagePainter(it.img),
                    contentDescription = "User image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
                        .clip(
                            CircleShape
                        ),
                )
                Column {
                    Text(
                        text = it.username,
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text = it.name,
                        color = colorResource(R.color.colorDetail),
                        modifier = Modifier.padding(
                            start = 16.dp,
                            bottom = 8.dp
                        ),
                        fontSize = 14.sp
                    )
                }
            })
            Divider()
        })
    }

    @Composable
    private fun ContactsTitle() {
        Text(
            text = stringResource(R.string.title),
            fontSize = 28.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 48.dp)
        )
    }
}