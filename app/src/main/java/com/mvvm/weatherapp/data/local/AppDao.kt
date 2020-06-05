package com.mvvm.weatherapp.data.local

import androidx.room.*
import com.mvvm.weatherapp.data.model.CityModel
import io.reactivex.Observable

@Dao
interface AppDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg cityInfo: CityModel): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(cities: List<CityModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityModel): Long

    @Delete
    fun delete(city: CityModel)

    @Query("Select *FROM cityModel WHERE city LIKE :cityName")
    fun searchByName(cityName: String): List<CityModel>

    @Query("Select *FROM cityModel WHERE  added = 1")
    fun getFavourites(): List<CityModel>

    @Query("Select *FROM cityModel WHERE id =:id")
    fun getCityWithId(id: Int):CityModel

    @Query("SELECT *FROM cityModel")
    fun fetchCities(): List<CityModel>

    @Query("DELETE FROM cityModel")
    fun deleteCityDB()

}