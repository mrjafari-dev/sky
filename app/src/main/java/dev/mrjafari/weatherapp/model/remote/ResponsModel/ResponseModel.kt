package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val data :ArrayList<ChildModel>
)
