package br.com.zup.simcityrickandmorty.ui.characterslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.singleliveevent.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = CharacterUseCase(application)
    val listState = SingleLiveEvent<ViewState<List<CharactersResult>>>()

    fun getCharacterList(){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getCharactersAPI()
                }
                listState.value = response
            }catch(e:Exception){
                listState.value = ViewState.Error(Throwable(ERROR))
            }
        }
    }
}