package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class Datum(
    val windCdir: String,
    val rh: Long,
    val pod: String,
    val lon: Double,
    val pres: Double,
    val timezone: String,
    val obTime: String,
    val countryCode: String,
    val clouds: Long,
    val ts: Long,
    val solarRAD: Long,
    val stateCode: String,
    val cityName: String,
    val windSpd: Long,
    val slp: Double,
    val windCdirFull: String,
    val sunrise: String,
    val appTemp: Double,
    val dni: Long,
    val vis: Long,
    val sources: List<String>,
    val hAngle: Long,
    val dewpt: Double,
    val snow: Long,
    val aqi: Long,
    val dhi: Long,
    val windDir: Long,
    val elevAngle: Double,
    val ghi: Long,
    val precip: Long,
    val sunset: String,
    val lat: Double,
    val uv: Long,
    val datetime: String,
    val temp: Double,
    val weather: Weather,
    val station: String
)
