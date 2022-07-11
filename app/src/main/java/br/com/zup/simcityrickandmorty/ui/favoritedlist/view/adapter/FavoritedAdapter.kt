package br.com.zup.simcityrickandmorty.ui.favoritedlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class FavoritedAdapter (
    private var charactersList: MutableList<CharactersResult>,
    private val clickDetail:(character: CharactersResult) -> Unit
) : RecyclerView.Adapter<FavoritedAdapter.ViewHolder>(){

    class ViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(characterResult: CharactersResult){
            binding.tvCharacterName.text = characterResult.name
            Picasso.get().load(characterResult.image).into(binding.ivCharacterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = charactersList[position]
        holder.showInfo(character)
        holder.binding.Item.setOnClickListener{
            clickDetail(character)
        }
        holder.showInfo(character)
    }

    override fun getItemCount() = charactersList.size

    fun updateList(newList:MutableList<CharactersResult>){
        charactersList = newList
        notifyDataSetChanged()
    }
}