package br.com.zup.simcityrickandmorty.ui.characterslist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private var characterList: MutableList<CharactersResult>,
    private val clickDetail:(characterResult: CharactersResult) -> Unit,
) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>(){

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
        val characters = characterList[position]
        holder.showInfo(characters)
        holder.binding.Item.setOnClickListener{
            clickDetail(characters)
        }
    }

    override fun getItemCount() = characterList.size

    fun updateList(newList:MutableList<CharactersResult>){
        characterList = newList
        notifyDataSetChanged()
    }
}