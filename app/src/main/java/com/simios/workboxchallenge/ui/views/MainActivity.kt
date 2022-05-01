package com.simios.workboxchallenge.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.simios.comicsapp.ui.adapters.UserListAdapter
import com.simios.workboxchallenge.ui.viewmodels.UsersViewModel

import com.simios.workboxchallenge.databinding.ActivityMainBinding
import com.simios.workboxchallenge.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private val userViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel.onCreate()
        loadLastedComics()
    }

    private fun loadLastedComics() {
        userViewModel.listUsers.observe(this, Observer { users ->
            if (users.isNotEmpty()) {
                initRecyclerView(users)
            } else {
                Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(comics: List<User>) = with((binding.comicsList)) {
        layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(comics) { onItemSelected(it) }
    }

    fun onItemSelected(user: User) {
        val intent = Intent(this, DetailUserActivity::class.java).apply {
            putExtra("name", user.name)
            putExtra("image", user.picture)
            putExtra("gender", user.gender)
            putExtra("age", user.age)
            putExtra("city", user.location)
            putExtra("email", user.email)
        }
        startActivity(intent)
    }
}