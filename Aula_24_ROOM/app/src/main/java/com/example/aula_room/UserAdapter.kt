package com.example.aula_room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aula_room.databinding.RowUserBinding

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var userList: List<UserModel> = listOf()
    private lateinit var listener: OnUserListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val item = RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(list: List<UserModel>) {
        userList = list
        notifyDataSetChanged()
    }

    fun attachListener(userListener: OnUserListener) {
        listener = userListener
    }
}