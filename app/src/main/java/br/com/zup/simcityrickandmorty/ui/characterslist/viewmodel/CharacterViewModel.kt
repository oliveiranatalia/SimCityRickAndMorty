package br.com.zup.simcityrickandmorty.ui.characterslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.data.model.CharacterResponse
import kotlinx.coroutines.launch

class CharacterViewModel:ViewModel() {
    private val _response = MutableLiveData<CharacterResponse>()
    val response = _response

    fun getCharacterList(){
        viewModelScope.launch {

        }
    }
}