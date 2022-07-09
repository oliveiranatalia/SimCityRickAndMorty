package br.com.zup.simcityrickandmorty.domain.usecase

import android.app.Application
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.datasource.local.CharactersDatabase
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.repository.CharacterRepository
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val charDAO = CharactersDatabase.getDatabase(application).charDao()
    private val repository = CharacterRepository(charDAO)

    suspend fun getLocalList():ViewState<List<CharactersResult>>{
        return try{
            val charactersList = repository.getLocalList()
            ViewState.Success(charactersList)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }
    suspend fun getCharactersAPI(): ViewState<List<CharactersResult>> {
        return try{
            val response = repository.getCharactersList()
            ViewState.Success(response.charactersResults)
        }catch(e:Exception){
            getLocalList()
        }
    }

    suspend fun getFavoritedList():ViewState<List<CharactersResult>>{
        return try{
            val character = repository.getFavoritedList()
            ViewState.Success(character)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }
    suspend fun updateFavoritedList(character:CharactersResult):ViewState<CharactersResult>{
        return try{
            repository.updateFavoritedList(character)
            ViewState.Success(character)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }
}