package dev.mrjafari.weatherapp.model.remote.ResponsModel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val data: List<Data>,
    val count: Long,

 //   val minutely: List<Minutely>
)
