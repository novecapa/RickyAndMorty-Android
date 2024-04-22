package com.example.rickandmorty.domain.entities.characters

import android.os.Parcelable
import com.example.rickandmorty.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val location: LocationEntity,
    val episode: List<String>
): Parcelable

val CharacterEntity.statusColor: Int
    get() {
        return if (status.lowercase() == "alive") {
            R.drawable.bubble_status_alive
        } else if (status.lowercase() == "dead") {
            R.drawable.bubble_status_dead
        } else {
            R.drawable.bubble_status_unknow
        }
    }