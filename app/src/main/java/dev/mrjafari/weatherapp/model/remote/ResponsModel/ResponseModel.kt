package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val count: Long,
    val data: List<Datum>,
    val minutely: List<Minutely>
)
