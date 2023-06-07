package com.karsatech.overlordcharacter.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.karsatech.overlordcharacter.databinding.ActivityDetailBinding
import com.karsatech.overlordcharacter.model.OverlordModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "List Character Overlord"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Tindakan yang ingin dilakukan ketika tombol "Back" ditekan
                supportFinishAfterTransition() // Mengakhiri activity dengan efek transisi
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupData() {
        val data = intent.getParcelableExtra<OverlordModel>("Character") as OverlordModel
        Glide.with(applicationContext)
            .load(data.img)
            .fitCenter()
            .into(binding.profileImageView)
        binding.nameTextView.text = data.name
        binding.descTextView.text = data.desc

        supportActionBar?.title = data.name
    }
}