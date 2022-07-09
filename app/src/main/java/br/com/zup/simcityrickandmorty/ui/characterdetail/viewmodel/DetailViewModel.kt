package br.com.zup.simcityrickandmorty.ui.characterdetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.model.SingleLiveEvent
import br.com.zup.simcityrickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = CharacterUseCase(application)
    val characterState = SingleLiveEvent<ViewState<List<CharactersResult>>>()

    suspend fun characterDetail(){

    }

}