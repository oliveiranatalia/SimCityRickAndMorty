package br.com.zup.simcityrickandmorty.ui.characterdetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.model.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = CharacterUseCase(application)
    val characterState = MutableLiveData<ViewState<List<CharactersResult>>>()
    val favState = SingleLiveEvent<ViewState<CharactersResult>>()

   suspend fun characterDetail(){
       try{
           val response = withContext(Dispatchers.IO){
               useCase.getCharactersAPI()
           }
           characterState.value = response
       }catch(e:Exception){
           characterState.value = ViewState.Error(Throwable(ERROR))
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