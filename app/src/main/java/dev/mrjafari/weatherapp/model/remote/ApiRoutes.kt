package dev.mrjafari.weatherapp.model.remote

object ApiRoutes {
    private const val BASE_URL = "https://api.weatherbit.io/v2.0"
    const val CityDetails = "$BASE_URL/current?lat=35.7796&lon=-78.6382&key=f24c70ecf7e740b0b51ebf6ff3097ec6&include=minutely"

}