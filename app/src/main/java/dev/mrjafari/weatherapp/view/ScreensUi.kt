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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import dev.mrjafari.weatherapp.ui.theme.fonts
import dev.mrjafari.weatherapp.ui.theme.green
import dev.mrjafari.weatherapp.ui.theme.shadow
import dev.mrjafari.weatherapp.ui.theme.white
import kotlinx.coroutines.launch

val showProgress =
    mutableStateOf(true)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController, countries: MutableList<CountryModel>,responseModel: ResponseModel) {
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
                                country.value = CountrySelected
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

                    CountrySelected = Preview_MultipleRadioButtons(SearchResultCountry)


                }


            }
        },
        sheetPeekHeight = 0.dp,

        ) {
        MainLayout(coroutineScope, bottomSheetScaffoldState, ContryName, navController,responseModel)
        if (showProgress.value)
        progress()
    }
}


@Composable
fun DetailScreen() {
    val Title = remember {
        mutableStateOf("")
    }
    val Text = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.1f)
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            TextField(value = Title.value, onValueChange = {
                Title.value = it
            }, modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = white,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                label = {
                    Text(text = "insert Title", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                })
            Button(onClick = { /*TODO*/ }, modifier = Modifier.offset(0.dp,10.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = green,

            ),
            shape = RoundedCornerShape(10.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp
                )
                ) {
                Text("Save")
            }
        }
        TextField(value = Title.value, onValueChange = {
            Title.value = it
        }, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1.0f)
            .offset(20.dp, 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = {
                Text(text = "type here somthing ", fontSize = 16.sp, fontWeight = FontWeight.Normal)
            })
    }
}

