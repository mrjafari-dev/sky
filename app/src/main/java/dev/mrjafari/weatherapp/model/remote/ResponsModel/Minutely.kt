package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class Minutely(
    val timestampUTC: String,
    val snow: Long,
    val temp: Double,
    val timestampLocal: String,
    val ts: Long,
    val precip: Long
)
