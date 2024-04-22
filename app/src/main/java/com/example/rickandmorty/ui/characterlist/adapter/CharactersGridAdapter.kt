package com.example.rickandmorty.ui.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entities.characters.CharacterEntity

class CharactersGridAdapter(
    val characters: List<CharacterEntity>,
    val listener: CharactersGridListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    interface CharactersGridListener {
        fun onSelectedCharacter(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersViewHolder(layoutInflater.inflate(R.layout.character_item_grid, parent, false))
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharactersViewHolder) {

            val character = characters[position]
            holder.render(character)

            holder.rlItemGrid.setOnClickListener {
                listener.onSelectedCharacter(position)
            }
        }
    }
}