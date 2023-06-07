package com.karsatech.overlordcharacter.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karsatech.overlordcharacter.model.OverlordModel
import com.karsatech.overlordcharacter.databinding.ItemCharacterBinding
import com.karsatech.overlordcharacter.ui.DetailActivity

class OverlordAdapter(private val listCharacter: ArrayList<OverlordModel>): RecyclerView.Adapter<OverlordAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(private val bind: ItemCharacterBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(data: OverlordModel) {
            bind.nameTextView.text = data.name
            bind.descTextView.text = data.desc

            Glide.with(itemView.context)
                .load(data.img)
                .fitCenter()
                .into(bind.profileImageView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("Character", data)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(bind.profileImageView, "image"),
                        Pair(bind.nameTextView, "name"),
                        Pair(bind.descTextView, "description"),
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = listCharacter.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listCharacter[position])
    }
}