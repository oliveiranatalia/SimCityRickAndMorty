package br.com.zup.simcityrickandmorty.ui.characterslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
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
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]}
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(arrayListOf(), ::goToDetail)
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
        viewModel.getCharacterList()
        observers()
        goToFavorited()
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
        viewModel.loading.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbLoading.isVisible = it.loading == true
                    binding.tvLoading.isVisible = it.loading == true
                }
                else -> {}
            }
        }
        viewModel.listState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success ->{
                    Toast.makeText(context,getString(R.string.favorite),Toast.LENGTH_LONG).show()}
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}",Toast.LENGTH_LONG).show()}
                else ->{}
            }
        }
    }
    private fun showRecycler(){
        binding.rvCharactersList.adapter = adapter
        binding.rvCharactersList.layoutManager = GridLayoutManager(context,2)
    }
    private fun goToDetail(char: CharactersResult){
        val bundle = bundleOf(CHARACTER to char)
        NavHostFragment.findNavController(this).navigate(R.id.action_charactersFragment_to_detailFragment,bundle)
    }
    private fun goToFavorited(){
        binding.ivFavoritedList.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_charactersFragment_to_favoritedListFragment)
        }
    }
}