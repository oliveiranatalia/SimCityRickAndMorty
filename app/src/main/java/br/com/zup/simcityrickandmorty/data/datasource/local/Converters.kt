package br.com.zup.simcityrickandmorty.data.datasource.local

import androidx.room.TypeConverter
import br.com.zup.simcityrickandmorty.data.model.Location
import br.com.zup.simcityrickandmorty.data.model.Origin
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun converterFromLocation(value:String): Location?{
        return Gson().fromJson(value,Location::class.java)
    }
    @TypeConverter
    fun converterFromLocationToJson(location: Location):String{
        return Gson().toJson(location)
    }
    @TypeConverter
    fun converterFromOrigin(value:String): Origin?{
        return Gson().fromJson(value,Origin::class.java)
    }
    @TypeConverter
    fun converterFromOriginToJson(origin: Origin):String{
        return Gson().toJson(origin)
    }
}