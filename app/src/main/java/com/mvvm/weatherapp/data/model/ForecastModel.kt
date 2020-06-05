package com.mvvm.weatherapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("lon")
    val lon: Float?,
    @SerializedName("lat")
    val lat: Float?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?,
    @SerializedName("current")
    val current: Current?,
    @SerializedName("hourly")
    val hourly: List<Hourly>?,
    @SerializedName("daily")
    val daily: List<Daily>?
)


data class Daily(
    @SerializedName("dt")
    @Expose
    val dt: Int? = null,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null,
    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null,
    @SerializedName("temp")
    @Expose
    val temp: Temp? = null,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: FeelsLike? = null,
    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Float? = null,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float? = null,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Int? = null,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null,
    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,
    @SerializedName("uvi")
    @Expose
    val uvi: Float? = null,
    @SerializedName("rain")
    @Expose
    val rain: Float? = null,
    @SerializedName("snow")
    @Expose
    val snow: Float? = null
)

data class Hourly(
    @SerializedName("dt")
    @Expose
    val dt: Int? = null,
    @SerializedName("temp")
    @Expose
    val temp: Float? = null,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float? = null,
    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Float? = null,
    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float? = null,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Int? = null,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null,
    @SerializedName("rain")
    @Expose
    val rain: Rain? = null
)


data class Rain(
    @SerializedName("1h")
    @Expose
    val _1h: Float? = null
)


data class FeelsLike(
    @SerializedName("day")
    @Expose
    val day: Float? = null,
    @SerializedName("night")
    @Expose
    val night: Float? = null,
    @SerializedName("eve")
    @Expose
    val eve: Float? = null,
    @SerializedName("morn")
    @Expose
    val morn: Float? = null
)


data class Current(
    @SerializedName("dt")
    @Expose
    val dt: Int? = null,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null,
    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null,
    @SerializedName("temp")
    @Expose
    val temp: Float? = null,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float? = null,
    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Float? = null,
    @SerializedName("uvi")
    @Expose
    val uvi: Float? = null,
    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float? = null,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Int? = null,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null
)


data class Temp(
    @SerializedName("day")
    @Expose
    val day: Float? = null,
    @SerializedName("min")
    @Expose
    val min: Float? = null,
    @SerializedName("max")
    @Expose
    val max: Float? = null,
    @SerializedName("night")
    @Expose
    val night: Float? = null,
    @SerializedName("eve")
    @Expose
    val eve: Float? = null,
    @SerializedName("morn")
    @Expose
    val morn: Float? = null
)


data class Weather(
    @SerializedName("id")
    val id: String?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?
)
