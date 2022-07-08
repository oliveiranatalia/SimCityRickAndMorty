package br.com.zup.simcityrickandmorty.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters")
data class CharactersResult(
    @SerializedName("created")
    val created: String = "",
    @SerializedName("episode")
    val episode: List<String> = listOf(),
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("location")
    val location: Location = Location(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin")
    val origin: Origin = Origin(),
    @SerializedName("species")
    val species: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = "",
    var isFavorite:Boolean
) : Parcelable