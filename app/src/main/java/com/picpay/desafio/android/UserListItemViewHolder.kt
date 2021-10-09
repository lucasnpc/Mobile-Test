package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.remote.dto.UserResponse
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
   itemView: ListItemUserBinding
) : RecyclerView.ViewHolder(itemView.root) {

    val view = itemView

    fun bind(userResponse: UserResponse) {
        view.name.text = userResponse.name
        view.username.text = userResponse.username
        view.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(userResponse.img)
            .error(R.drawable.ic_round_account_circle)
            .into(view.picture, object : Callback {
                override fun onSuccess() {
                    view.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    view.progressBar.visibility = View.GONE
                }
            })
    }
}