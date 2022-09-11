package dev.mrjafari.weatherapp.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import dev.mrjafari.weatherapp.ui.theme.shadow
import dev.mrjafari.weatherapp.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun Navigation(countries: MutableList<CountryModel>, responsemodel: MutableState<ResponseModel> ) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.rout) {
        composable(Screens.MainScreen.rout) {
            Log.i("51544545","nav"+ responsemodel.value.data[0].country_code)
            MainScreen(navController = navController,countries,responsemodel.value)
        }
        composable(
            route = Screens.DetailScreen.rout,
        ) {
            DetailScreen()
        }
    }
}




