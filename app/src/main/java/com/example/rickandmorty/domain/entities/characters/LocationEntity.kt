package com.example.rickandmorty.domain.entities.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationEntity(
    val name: String,
    val url: String
): Parcelable
