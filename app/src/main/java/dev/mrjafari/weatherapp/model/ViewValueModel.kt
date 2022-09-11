package dev.mrjafari.weatherapp.model

data class ViewValueModel(
    val country : String,
    var city :String,
    val status : String,
    val date :String,
    val d :String,
    val windSpeed :String,
    val seaLevel :String,
    val verbalWind:String,
    val realitive :String,
    val airQuality:String
)