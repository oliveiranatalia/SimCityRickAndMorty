package br.com.zup.simcityrickandmorty.ui.favoritedlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.const.CHARACTER
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.FragmentFavoritedListBinding
import br.com.zup.simcityrickandmorty.ui.favoritedlist.view.adapter.FavoritedAdapter
import br.com.zup.simcityrickandmorty.ui.favoritedlist.viewmodel.FavoritedViewModel

class FavoritedListFragment : Fragment() {
    private lateinit var binding: FragmentFavoritedListBinding
    private val viewModel: FavoritedViewModel by lazy {
        ViewModelProvider(this)[FavoritedViewModel::class.java]
    }
    private val adapter: FavoritedAdapter by lazy { FavoritedAdapter(arrayListOf(), ::gotToDetail) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritedListBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun showRecycler() {
        binding.rvCharactersList.adapter = adapter
        binding.rvCharactersList.layoutManager = GridLayoutManager(context, 2)
    }
    private fun disfavor(character: CharactersResult){
        viewModel.disfavorCharacter(character)
    }
    private fun gotToDetail(character: CharactersResult){
        val bundle = bundleOf(CHARACTER to character)
        NavHostFragment.findNavController(this).navigate(R.id.action_favoritedListFragment_to_detailFragment, bundle)
    }
}