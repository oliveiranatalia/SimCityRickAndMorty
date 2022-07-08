package br.com.zup.simcityrickandmorty.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class Character(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "status")
    var status:String,

    @ColumnInfo(name = "specie")
    var specie:String,

    @ColumnInfo(name = "gender")
    var gender:String,

    var isFavorite: Boolean = false
)