package com.karsatech.overlordcharacter.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.karsatech.overlordcharacter.adapter.OverlordAdapter
import com.karsatech.overlordcharacter.model.OverlordModel
import com.karsatech.overlordcharacter.R
import com.karsatech.overlordcharacter.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val overlordCharacterList = ArrayList<OverlordModel>()
    private lateinit var adapter: OverlordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "List Character Overlord"

        setOnClick()
        setupRecyclerView()
        addItemsFromJSON(this)
        setupAdapter()
    }

    private fun setOnClick() {
        binding.fabProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readJSONDataFromRaw(context: Context, resourceId: Int): String {
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return stringBuilder.toString()
    }

    private fun addItemsFromJSON(context: Context) {
        try {
            val jsonDataString = readJSONDataFromRaw(context, R.raw.overlord)
            val jsonArray = JSONArray(jsonDataString)

            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val name = itemObj.getString("name")
                val image = itemObj.getString("image")
                val desc = itemObj.getString("desc")

                val overlord = OverlordModel(name, image, desc)
                overlordCharacterList.add(overlord)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupAdapter() {
        adapter = OverlordAdapter(overlordCharacterList)
        binding.rvCharacter.adapter = adapter
    }
}