package br.com.zup.simcityrickandmorty.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.const.CHARACTER
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.FragmentDetailBinding
import br.com.zup.simcityrickandmorty.ui.characterdetail.viewmodel.DetailViewModel
import br.com.zup.simcityrickandmorty.ui.home.view.HomeActivity
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun dataRecover(){
        val character = arguments?.getParcelable<CharactersResult>(CHARACTER)
        character?.let {
            Picasso.get().load(character.image).into(binding.ivDetailImage)
            binding.tvDetailCharacterName.text = "Nome: ${it.name}"
            binding.tvDetailCharacterStatus.text = "Status: ${it.status}"
            binding.tvDetailCharacterGender.text = "Gênero: ${it.gender}"
            binding.tvDetailCharacterSpecie.text = "Espécie: ${it.species}"

            (activity as HomeActivity).title.let { character.name }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRecover()
    }
    private fun sendToFavoritedList(charactersResult: CharactersResult){
        viewModel.favoriteCharacter(charactersResult)
    }
}