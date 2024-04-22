package com.example.rickandmorty.ui.characterdetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import com.example.rickandmorty.domain.entities.characters.statusColor

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val CHARACTER_DETAIL = "character_detail"
    }

    lateinit var binding: ActivityCharacterDetailBinding
    lateinit var viewModel: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.getParcelableExtra<CharacterEntity>(CHARACTER_DETAIL)?.let {
            viewModel = CharacterDetailViewModel(it)
        }

        setupView()
        setupObservers()
        viewModel.fetchRelated()
    }

    private fun setupObservers() {
        viewModel.refreshRelated.observeForever {

        }
    }

    private fun setupView() {
        val character = viewModel.characterDetail

        // Image
        Glide.with(binding.imageItem.context)
            .load(character.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageItem)

        binding.viewStatus.setBackgroundResource(character.statusColor)
        binding.tvStatus.text = character.status

        binding.tvName.text = character.name
        binding.tvType.text = character.type
        binding.tvSpecie.text = character.species
        binding.tvGender.text = character.gender
        binding.tvEpisodes.text = "Appears on ${character.episode.count()} episodes"
    }
}