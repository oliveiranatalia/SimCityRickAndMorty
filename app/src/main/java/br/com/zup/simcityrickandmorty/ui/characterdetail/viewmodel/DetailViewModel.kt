package br.com.zup.simcityrickandmorty.ui.characterdetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.R
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.singleliveevent.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = CharacterUseCase(application)
    val favoritedList = SingleLiveEvent<ViewState<List<CharactersResult>>>()
    val favorite = SingleLiveEvent<ViewState<CharactersResult>>()
    val disfavor = SingleLiveEvent<ViewState<CharactersResult>>()

    fun updateFavoritedList(character: CharactersResult){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.updateFavoritedList(character)
                }
                favorite.value = response
            }catch(e:Exception){
                favoritedList.value = ViewState.Error(Throwable(R.string.favorite_error.toString()))
            }
        }
    }
    fun disfavor(character: CharactersResult){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.updateFavoritedList(character)
                }
                disfavor.value = response
            }catch(e:Exception){
                disfavor.value = ViewState.Error(Throwable(R.string.disfavor_error.toString()))
            }
        }
    }
}