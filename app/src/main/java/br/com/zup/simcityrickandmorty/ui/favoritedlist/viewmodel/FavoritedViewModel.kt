package br.com.zup.simcityrickandmorty.ui.favoritedlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.model.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritedViewModel(application: Application) : AndroidViewModel(application){
    private val useCase = CharacterUseCase(application)
    val favorite = SingleLiveEvent<ViewState<List<CharactersResult>>>()
    val disfavor = SingleLiveEvent<ViewState<CharactersResult>>()
    val favError = R.string.favorite_error
    val disfavError = R.string.disfavor_error

    fun getFavoritedList(){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getFavoritedList()
                }
                favorite.value = response
            }catch(e:Exception){
                favorite.value = ViewState.Error(Throwable(favError.toString()))
            }
        }
    }
    fun disfavorCharacter(character: CharactersResult){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.updateFavoritedList(character)
                }
                disfavor.value = response
            }catch(e:Exception){
                disfavor.value = ViewState.Error(Throwable(disfavError.toString()))
            }
        }
    }
}