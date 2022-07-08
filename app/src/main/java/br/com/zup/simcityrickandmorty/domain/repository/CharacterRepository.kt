package br.com.zup.simcityrickandmorty.domain.repository

import br.com.zup.simcityrickandmorty.data.datasource.local.dao.CharactersDAO
import br.com.zup.simcityrickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.simcityrickandmorty.data.model.CharacterResponse
import br.com.zup.simcityrickandmorty.data.model.CharactersResult

class CharacterRepository(private val charDAO: CharactersDAO){

    suspend fun getCharactersList():CharacterResponse{
        return RetrofitService.apiService.getCharactersList()
    }
    suspend fun getLocalList():List<CharactersResult> = charDAO.getCharactersList()
}