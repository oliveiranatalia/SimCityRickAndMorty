package br.com.zup.simcityrickandmorty.data.datasource.remote

import br.com.zup.simcityrickandmorty.data.model.CharacterResponse
import retrofit2.http.GET

interface CharacterAPI {
    @GET("character")
    suspend fun getCharactersListAPI(): CharacterResponse
}