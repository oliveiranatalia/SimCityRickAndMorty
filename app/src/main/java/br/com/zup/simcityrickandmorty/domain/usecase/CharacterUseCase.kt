package br.com.zup.simcityrickandmorty.domain.usecase

import android.app.Application
import br.com.zup.simcityrickandmorty.const.ERROR
import br.com.zup.simcityrickandmorty.data.datasource.local.CharactersDatabase
import br.com.zup.simcityrickandmorty.data.model.CharactersResult
import br.com.zup.simcityrickandmorty.domain.repository.CharacterRepository
import br.com.zup.simcityrickandmorty.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val characterAO = CharactersDatabase.getDatabase(application).characterAO()
    private val repository = CharacterRepository(characterAO)

    suspend fun getCharactersAPI(): ViewState<List<CharactersResult>> {
        return try{
            val response = repository.getCharactersListAPI()
            repository.insertDatabaseList(response.charactersResults)
            ViewState.Success(response.charactersResults)
        }catch(e:Exception){
            getLocalList()
        }
    }

    suspend fun getFavoritedList():ViewState<List<CharactersResult>>{
        return try{
            val charactersFavorited = repository.getFavoritedList()
            ViewState.Success(charactersFavorited)
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

    suspend fun getLocalList():ViewState<List<CharactersResult>>{
        return try{
            val charactersList = repository.getLocalList()
            ViewState.Success(charactersList)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }
}