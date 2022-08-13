package dev.mrjafari.weatherapp.model.remote.ResponsModel

import kotlinx.serialization.Serializable

@Serializable
data class ChildModel (
    val wind_cdir :String ,
    val rh :Int
        )

