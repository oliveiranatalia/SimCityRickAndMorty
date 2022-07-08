package br.com.zup.simcityrickandmorty.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.zup.simcityrickandmorty.data.model.CharactersResult

@Dao
interface CharactersDAO {

    @Query("SELECT * FROM characters ORDER BY episode ASC")
    fun getCharactersList():List<CharactersResult>

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavoritedCharacters():List<CharactersResult>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateFavoritedList(char:CharactersResult)
}