package com.picpay.desafio.android.ui.contactsListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.remote.dto.UserResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsListFragment : Fragment() {

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
        val uiState = viewModel.state.observeAsState().value
        Scaffold(
            content = {
                Box(modifier = Modifier
                    .background(colorResource(id = R.color.colorPrimaryDark))
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = {
                        uiState?.let {
                            if (it.success)
                                CreateContactsList(users)
                            if (it.isLoading)
                                CreateLoadingProgressBar()
                            if (it.failed)
                                CreateErrorScreen()
                        }
                    })
            }
        )
    }

    @Composable
    private fun CreateContactsList(users: ArrayList<UserResponse>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ContactsTitle()
            }
            items(users) {
                ContactsItems(it)
            }
        }
    }

    @Composable
    private fun CreateLoadingProgressBar() {
        CircularProgressIndicator(color = colorResource(id = R.color.colorAccent))
    }

    @Composable
    private fun CreateErrorScreen() {
        Text(
            text = "Alguma coisa deu Errado",
            color = Color.White,
            fontSize = 24.sp
        )
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

    @Composable
    private fun ContactsItems(it: UserResponse) {
        Column(modifier = Modifier.fillMaxWidth(), content = {
            Row(content = {
                Image(
                    painter = rememberImagePainter(
                        data = it.img,
                        builder = {
                            crossfade(1500)
                            transformations(CircleCropTransformation())
                            placeholder(R.drawable.ic_round_account_circle)
                        }
                    ),
                    contentDescription = "User image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
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
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                        fontSize = 14.sp
                    )
                }
            })
            Divider()
        })
    }
}