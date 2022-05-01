package com.simios.comicsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simios.workboxchallenge.R
import com.simios.workboxchallenge.databinding.UserItemBinding
import com.simios.workboxchallenge.domain.model.User
import com.squareup.picasso.Picasso

class UserListAdapter(
    private val list: List<User>,
    private val onClickListener: (User) -> Unit
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val TAG = UserListAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, onClickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = UserItemBinding.bind(view)

        fun bind(userModel: User, onClickListener: (User) -> Unit) {
            binding.comicTitle.text = userModel.name
            Picasso.get().load(userModel.picture).into(binding.comicImage)
            itemView.setOnClickListener {
                onClickListener(userModel)
            }
        }
    }

    private fun getItem(position: Int): User {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}