package dev.mrjafari.weatherapp.model

data class ViewDataModel(
    val today : String,
    val status :String,
    val icon :String,
    val listmodel :List<ListModel>

)
