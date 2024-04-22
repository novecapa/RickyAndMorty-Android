package com.example.rickandmorty.ui.characterlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityCharacterListBinding
import com.example.rickandmorty.ui.characterdetail.CharacterDetailActivity
import com.example.rickandmorty.ui.characterlist.adapter.CharactersGridAdapter

class CharacterListActivity : AppCompatActivity(), CharactersGridAdapter.CharactersGridListener {

    private lateinit var binding: ActivityCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var adapter: CharactersGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupRecycler()
        viewModel.fetchCharacters()
    }

    private fun setupObservers() {
        viewModel.refreshList.observeForever {
            binding.rvCharactersGrid.adapter?.notifyDataSetChanged()
        }
        viewModel.scrollToTop.observeForever {
            if (it) { binding.rvCharactersGrid.scrollToPosition(0) }
        }
        viewModel.showIndicator.observeForever {
            if (it) { showActivityIndicator() } else { hideActivityIndicator() }
        }
        viewModel.handleError.observeForever {
            showAlertError(it)
        }
    }

    private fun setupRecycler() {
        val rvCharactersGrid = binding.rvCharactersGrid
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvCharactersGrid.layoutManager = gridLayoutManager
        adapter = CharactersGridAdapter(viewModel.characterList, this)
        rvCharactersGrid.adapter = adapter
        rvCharactersGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.characterList.size - 1) {
                    viewModel.fetchCharacters()
                }
            }
        })
    }

    override fun onSelectedCharacter(position: Int) {
        val character = viewModel.characterList[position]
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CharacterDetailActivity.CHARACTER_DETAIL, character)
        }
        startActivity(intent)
    }

    private fun showActivityIndicator() {
        binding.llHudLoader.visibility = View.VISIBLE
    }

    private fun hideActivityIndicator() {
        binding.llHudLoader.visibility = View.GONE
    }

    private fun showAlertError(errorMessage: String) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setTitle(getString(R.string.error_title))
            .setMessage(errorMessage)
            .setPositiveButton(getString(R.string.Ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}