package br.com.zup.simcityrickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterAPI(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val results: List<Result> = listOf()
)