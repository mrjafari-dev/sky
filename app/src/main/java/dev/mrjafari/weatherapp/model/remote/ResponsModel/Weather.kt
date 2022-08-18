package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val icon: String,
    val code: Long,
    val description: String
)
