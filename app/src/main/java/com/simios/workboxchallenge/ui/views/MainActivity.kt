package com.simios.workboxchallenge.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.simios.workboxchallenge.databinding.ActivityMainBinding
import com.simios.workboxchallenge.domain.model.User
import com.simios.workboxchallenge.ui.adapters.UserListAdapter
import com.simios.workboxchallenge.ui.viewmodels.UsersViewModel
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
        loadingObserver()
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

    private fun loadingObserver() {
        userViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }

    private fun initRecyclerView(comics: List<User>) = with((binding.comicsList)) {
        layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(comics) { onItemSelected(it) }
    }

    private fun onItemSelected(user: User) {
        val intent = Intent(this, DetailUserActivity::class.java).apply {
            putExtra("name", user.name)
            putExtra("picture", user.picture)
            putExtra("gender", user.gender)
            putExtra("age", user.age)
            putExtra("city", user.location)
            putExtra("email", user.email)
            putExtra("phone", user.phone)
        }
        startActivity(intent)
    }
}