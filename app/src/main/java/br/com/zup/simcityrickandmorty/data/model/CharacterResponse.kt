package br.com.zup.simcityrickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val charactersResults: List<CharactersResult>
)