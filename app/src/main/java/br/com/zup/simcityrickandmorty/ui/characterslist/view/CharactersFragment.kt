package br.com.zup.simcityrickandmorty.ui.characterslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.const.CHARACTER
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.FragmentCharactersBinding
import br.com.zup.simcityrickandmorty.ui.characterslist.view.adapter.CharactersAdapter
import br.com.zup.simcityrickandmorty.ui.characterslist.viewmodel.CharacterViewModel
import br.com.zup.simcityrickandmorty.ui.home.view.HomeActivity
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]}

    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(arrayListOf(), ::goToDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacterList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecycler()
        observers()
        goToFavoritedList()
        (activity as HomeActivity).supportActionBar?.hide()
    }

    private fun observers() {
        viewModel.listState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateList(it.data.toMutableList())}
                is ViewState.Error -> {
                    Toast.makeText(context, ERROR, Toast.LENGTH_LONG).show()}
                else -> {}
            }
        }
    }
    private fun showRecycler(){
        binding.rvCharactersList.adapter = adapter
        binding.rvCharactersList.layoutManager = GridLayoutManager(context,2)
    }
    private fun goToDetail(character: CharactersResult){
        val bundle = bundleOf(CHARACTER to character)
        NavHostFragment.findNavController(this).navigate(R.id.action_charactersFragment_to_detailFragment,bundle)
    }
    private fun goToFavoritedList(){
        binding.fabFavoritedList.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_charactersFragment_to_favoritedListFragment)
        }
    }
}