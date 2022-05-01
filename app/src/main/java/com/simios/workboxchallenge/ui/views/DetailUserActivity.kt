package com.simios.workboxchallenge.ui.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simios.workboxchallenge.databinding.ActivityDetailUserBinding
import com.squareup.picasso.Picasso

class DetailUserActivity : AppCompatActivity() {
    val TAG = "DetailUserActivity"
    private lateinit var binding: ActivityDetailUserBinding
    var name: String? = null
    var picture: String? = null
    var gender: String? = null
    var city: String? = null
    var age: String? = null
    var phone: String? = null
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setupUi()
    }

    private fun getData() {
        if (intent.hasExtra("name") && intent.hasExtra("picture") &&
            intent.hasExtra("gender") && intent.hasExtra("age") &&
            intent.hasExtra("city") && intent.hasExtra("phone")
            && intent.hasExtra("email")
        ) {
            name = intent.getStringExtra("name")
            picture = intent.getStringExtra("picture")
            gender = intent.getStringExtra("gender")
            age = intent.getStringExtra("age")
            city = intent.getStringExtra("city")
            phone = intent.getStringExtra("phone")
            email = intent.getStringExtra("email")
        } else Toast.makeText(this, "No Data.", Toast.LENGTH_LONG).show()
    }

    private fun setupUi() {
        binding.userName.text = name
        binding.userGender.text = gender
        binding.userAge.text = age
        binding.userEmail.text = email
        binding.userPhone.text = phone
        binding.userCity.text = city
        Picasso.get().load(picture).into(binding.userPicture)
    }
}