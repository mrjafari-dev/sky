package dev.mrjafari.weatherapp.model.remote.RequestModel

import kotlinx.serialization.Serializable

@Serializable
data class RequestModel(
    val country :String,
    val city :String,
    val key :String

)
