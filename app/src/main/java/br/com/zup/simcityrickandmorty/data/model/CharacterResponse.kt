package br.com.zup.simcityrickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val charactersResults: List<CharactersResult>
)