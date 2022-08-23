package dev.mrjafari.weatherapp.model.remote.ResponsModel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val wind_cdir: String,
    val rh: Long,
    val pod: String,
    val lon: Double,
    val pres: Double,
    val timezone: String,
    val ob_time: String,
    val country_code: String,
    val clouds  : Long,
    val ts: Long,
    val solar_rad: Long,
    val state_code: String,
    val city_name: String,
    val wind_spd: Float,
    val slp: Double,
    val wind_cdir_full: String,
    val sunrise: String,
    val app_temp: Double,
    val dni: Long,
    val vis: Long,
    val sources: List<String>,
    val h_angle: Long,
    val dewpt: Double,
        val snow: Long,
    val aqi: Long,
    val dhi: Long,
    val wind_dir: Long,
    val elev_angle: Double,
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
