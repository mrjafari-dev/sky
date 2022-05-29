package dev.mrjafari.weatherapp.view

import androidx.navigation.NavArgs

sealed class Screens(val rout :String){
    object MainScreen :Screens("MainScreen")
    object DetailScreen :Screens("DetailScreen")
}
