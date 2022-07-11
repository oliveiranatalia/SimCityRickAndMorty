package br.com.zup.simcityrickandmorty.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.const.CHARACTER
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.FragmentDetailBinding
import br.com.zup.simcityrickandmorty.ui.characterdetail.viewmodel.DetailViewModel
import br.com.zup.simcityrickandmorty.ui.home.view.HomeActivity
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRecover()
        observers()
    }

    private fun dataRecover() {
        val character = arguments?.getParcelable<CharactersResult>(CHARACTER)

        character?.let {
            Picasso.get().load(character.image).into(binding.ivDetailImage)
            binding.tvDetailCharacterName.text = "Nome: ${it.name}"
            binding.tvDetailCharacterStatus.text = "Status: ${it.status}"
            binding.tvDetailCharacterGender.text = "Gênero: ${it.gender}"
            binding.tvDetailCharacterSpecie.text = "Espécie: ${it.species}"

            (activity as HomeActivity).supportActionBar?.title = it.name

            /** Para recuperar se o personagem está ou não favoritado **/
            binding.ivIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context,
                    if(character.isFavorite) R.drawable.ic_favorite
                    else R.drawable.ic_disfavor)
            )
            sendToFavoritedList(character)
        }
    }

    private fun sendToFavoritedList(character: CharactersResult) {
        binding.ivIcon.setOnClickListener{

            character.isFavorite = !character.isFavorite
            viewModel.updateFavoritedList(character)
            viewModel.disfavor(character)

            /** Para favoritar ou desfavoritar o personagem **/
            binding.ivIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context,
                    if(character.isFavorite) R.drawable.ic_favorite
                    else R.drawable.ic_disfavor)
            )
        }
    }
    private fun observers(){
        viewModel.favorite.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    if(it.data.isFavorite){
                        Toast.makeText(context,R.string.favorite, Toast.LENGTH_SHORT).show()
                    }}
                is ViewState.Error -> {
                    Toast.makeText(context,it.throwable.message, Toast.LENGTH_SHORT).show()}
                else -> {}
            }
        }
        viewModel.disfavor.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    if (!it.data.isFavorite) {
                        Toast.makeText(context, R.string.disfavor, Toast.LENGTH_SHORT).show()
                    }}
                is ViewState.Error -> {
                    Toast.makeText(context,it.throwable.message, Toast.LENGTH_SHORT).show()}
                else -> {}
            }
        }
    }
}