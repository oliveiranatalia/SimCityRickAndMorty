package br.com.zup.simcityrickandmorty.domain.repository

import br.com.zup.simcityrickandmorty.data.datasource.local.dao.CharactersDAO
import br.com.zup.simcityrickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.simcityrickandmorty.data.model.CharacterResponse
import br.com.zup.simcityrickandmorty.data.model.CharactersResult

class CharacterRepository(private val characterDAO: CharactersDAO){

    suspend fun getCharactersListAPI():CharacterResponse{
        return RetrofitService.apiService.getCharactersListAPI()
    }

    fun getLocalList():List<CharactersResult> = characterDAO.getCharactersList()

    suspend fun getFavoritedList():List<CharactersResult> = characterDAO.getFavoritedCharacters()

    suspend fun updateFavoritedList(character:CharactersResult){
        characterDAO.updateFavoritedList(character)
    }

    fun insertDatabaseList(characters: List<CharactersResult>){
        characterDAO.insertCharacters(characters)
    }
}