package br.com.zup.simcityrickandmorty.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters")
data class CharactersResult(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("created")
    val created: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String = "",
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