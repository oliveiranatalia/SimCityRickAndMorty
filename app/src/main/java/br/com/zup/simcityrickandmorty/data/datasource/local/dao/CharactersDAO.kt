package br.com.zup.simcityrickandmorty.data.datasource.local.dao

import androidx.room.*
import br.com.zup.simcityrickandmorty.data.model.CharactersResult

@Dao
interface CharactersDAO {

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavoritedCharacters():List<CharactersResult>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoritedList(character:CharactersResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharactersResult>)

    @Query("SELECT * FROM characters ORDER BY status ASC")
    fun getCharactersList():List<CharactersResult>
}