package com.fikrisandi.loginapp.ui.login.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fikrisandi.loginapp.databinding.UserListAdapterBinding
import com.fikrisandi.loginapp.model.user.User

class UserListAdapter : PagingDataAdapter<User, UserListAdapter.ViewHolder>(DIFF_CALLBACK) {


    class ViewHolder(private val binding: UserListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User) {
            binding.apply {
                tvUserEmail.text = data.email
                tvUserFullname.text = "${data.lastName}, ${data.firstName}"
                image.load(data.avatar) {
                    crossfade(true)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}