package br.com.zup.simcityrickandmorty.ui.characterslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.model.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = CharacterUseCase(application)
    val listState = SingleLiveEvent<ViewState<List<CharactersResult>>>()
    private val favState = SingleLiveEvent<ViewState<CharactersResult>>()
    val loading = SingleLiveEvent<ViewState<Boolean>>()

    fun getCharacterList(){
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getCharactersAPI()
                }
                listState.value = response
            }catch(e:Exception){
                listState.value = ViewState.Error(Throwable(ERROR))
            }finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }
    fun favoriteCharacter(charactersResult: CharactersResult){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.updateFavoritedList(charactersResult)
                }
                favState.value = response
            }catch (e:Exception){
                favState.value = ViewState.Error(Throwable(ERROR))
            }
        }
    }
}