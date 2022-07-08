package br.com.zup.simcityrickandmorty.ui.favoritedlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.simcityrickandmorty.databinding.FragmentFavoritedListBinding

class FavoritedListFragment : Fragment() {
    private lateinit var binding: FragmentFavoritedListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFavoritedListBinding.inflate(inflater,container,false)
        return binding.root
    }
}