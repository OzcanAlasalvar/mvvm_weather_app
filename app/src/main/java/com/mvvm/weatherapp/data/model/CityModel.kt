package com.mvvm.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cityModel")
data class CityModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int,
    @SerializedName("city")
    @Expose
    val city: String?,
    @SerializedName("lat")
    @Expose
    val lat: String?,
    @SerializedName("lon")
    @Expose
    val lon: String?,
    @SerializedName("country")
    @Expose
    val country: String?,
    @SerializedName("prov")
    @Expose
    val prov: String?,
    var added: Int = 0
)


@Entity(tableName = "favModel")
data class CityFavModel(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "lat")
    val lat: String?,
    @ColumnInfo(name = "lon")
    val lon: String?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "prov")
    val prov: String?
)





