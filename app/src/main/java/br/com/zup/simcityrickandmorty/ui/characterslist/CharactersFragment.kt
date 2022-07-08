package br.com.zup.simcityrickandmorty.ui.characterslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
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
        CharactersAdapter(arrayListOf(), ::goToDetail,::goToFavorited)
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
                is ViewState.Loading -> { TODO()}
                else -> {}
            }
        }
    }
    private fun goToDetail(char: CharactersResult){
        val bundle = bundleOf(CHARACTER to char)
    }
    private fun goToFavorited(char:CharactersResult){
        TODO()
    }
}