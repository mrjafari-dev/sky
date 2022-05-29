package dev.mrjafari.weatherapp.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import dev.mrjafari.weatherapp.ui.theme.shadow
import dev.mrjafari.weatherapp.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun Navigation(countries: MutableList<CountryModel>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.rout) {
        composable(Screens.MainScreen.rout) {
            MainScreen(navController = navController,countries)
        }
        composable(
            route = Screens.DetailScreen.rout,
        ) {
            DetailScreen()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController,countries: MutableList<CountryModel>) {
    var SearchResultCountry = countries
    var countryList = arrayListOf<CountryModel>()

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val ContryName = remember { mutableStateOf("IR") }
    var CountrySelected = ""
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 30.dp,
        sheetContent = {
            val searchBox = remember {
                mutableStateOf("")
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(white)
            ) {
                Column(Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(value = searchBox.value, onValueChange = {
                            searchBox.value = it
                            if (it.length > 1) {

                                countryList.clear()
                                countries.forEach { countryModel ->
                                    if (countryModel.country_name.contains(it)) {
                                        countryList.add(countryModel)
                                    }
                                }
                                SearchResultCountry = countryList
                            } else if (it.length < 1) {
                                SearchResultCountry = countries
                                countryList.clear()
                            }

                        }, modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .offset(15.dp, 20.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = shadow,
                                cursorColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(40.dp),
                            label = {
                                Text(text = "Search Country")
                            }
                        )

                        Button(
                            onClick = {
                                ContryName.value = CountrySelected
                                coroutineScope.launch {
                                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    else
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }, modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .offset(25.dp, 30.dp)
                        ) {
                            Text("Ok")
                        }
                    }

                    CountrySelected = dev.mrjafari.weatherapp.Preview_MultipleRadioButtons(SearchResultCountry)


                }


            }
        }, sheetPeekHeight = 0.dp,

        ) {
        MainLayout(coroutineScope, bottomSheetScaffoldState, ContryName,navController)

    }
}

@Composable
fun DetailScreen() {
    Column() {
        Text(text ="dfd")
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Preview_MultipleRadioButtons(countries: MutableList<CountryModel>): String {

    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val itemss = countries
    Column(Modifier.padding(8.dp)) {

        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 140.dp), modifier = Modifier.offset(0.dp,20.dp)) {
            items(itemss) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = isSelectedItem(it.country_code),
                            onClick = {
                                onChangeState(it.country_code)
                            },
                            role = Role.RadioButton
                        )
                        .padding(19.dp)
                ) {
                    val name: String
                    if (it.country_name.equals(""))
                        name = it.country_code
                    else name = it.country_name
                    RadioButton(
                        selected = isSelectedItem(it.country_code),
                        onClick = null
                    )
                    Text(

                        text = name,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1
                    )
                }
            }


        }
    }
    return selectedValue.value
}