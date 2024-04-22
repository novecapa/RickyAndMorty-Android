package com.example.rickandmorty.ui.characterlist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entities.characters.CharacterEntity

class CharactersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val rlItemGrid = view.findViewById<ConstraintLayout>(R.id.rlItemGrid)
    val imageItem = view.findViewById<ImageView>(R.id.imageItem)
    val tvName = view.findViewById<TextView>(R.id.tvName)

    fun render(character: CharacterEntity) {

        // Image
        Glide.with(imageItem.context)
            .load(character.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageItem)

        // Name
        tvName.text = character.name
    }
}