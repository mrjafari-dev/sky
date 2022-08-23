package dev.mrjafari.weatherapp.model.remote.ResponsModel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val count: Long,
    val data: List<Data>,
    val minutely: List<Minutely>
)
