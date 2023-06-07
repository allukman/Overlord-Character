package com.karsatech.overlordcharacter.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.karsatech.overlordcharacter.R
import com.karsatech.overlordcharacter.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.instagram.setOnClickListener(this)
        binding.github.setOnClickListener(this)
        binding.linkedin.setOnClickListener(this)

    }

    private fun implicitIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.instagram -> implicitIntent("https://www.instagram.com/allukman__/")
            R.id.github -> implicitIntent("https://github.com/allukman")
            R.id.linkedin -> implicitIntent("https://www.linkedin.com/in/allukman/")
        }
    }
}