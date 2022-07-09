package br.com.zup.simcityrickandmorty.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.const.CHARACTER
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.FragmentDetailBinding
import br.com.zup.simcityrickandmorty.ui.home.view.HomeActivity
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
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
            binding.tvDetailCharacterName.text = it.name
            binding.tvDetailCharacterStatus.text = it.status
            binding.tvDetailCharacterGender.text = it.gender
            binding.tvDetailCharacterSpecie.text = it.species

            binding.ivIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context,
                if(it.isFavorite) R.drawable.ic_favorite
                else R.drawable.ic_disfavor)
            )
            (activity as HomeActivity).supportActionBar?.title = it.name
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRecover()
    }
}