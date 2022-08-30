package dev.mrjafari.weatherapp.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.compose.rememberImagePainter
import dev.mrjafari.weatherapp.coloredShadow
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import dev.mrjafari.weatherapp.ui.theme.*
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainLayout(
    coroutine: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    ContryName: MutableState<String>,
    navController: NavController,
    responseModel: ResponseModel
) {
    Box(
        modifier = Modifier
            /*.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        white,
                        white
                    )
                )
            )*/.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 30.dp, 20.dp)
        ) {

            searchBox(coroutine, bottomSheetScaffoldState, ContryName,responseModel.data[0].city_name )
            val date = responseModel.data[0].datetime.split(":")
            val value = date[0].split("-")
            todayStatus("Today ,"+value[0]+"\n"+value[1]+"-"+value[2], responseModel.data[0].weather.description,responseModel.data[0].temp,responseModel.data[0].weather.icon)
            var list  = arrayListOf<String>()
            list.add("Wind speed (Default m/s) : "+responseModel.data[0].wind_spd)
            list.add("Sea level pressure (mb) : "+responseModel.data[0].slp)
            list.add("Verbal wind direction : "+responseModel.data[0].wind_cdir_full)
            list.add("Relative humidity (%) : "+responseModel.data[0].rh)
            list.add("Air Quality Index [US - EPA standard 0 - +500] : "+responseModel.data[0].aqi)
            Spacer(modifier = Modifier.height(30.dp))
            noteList(list)
        }
        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.DetailScreen.rout)


            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            backgroundColor = blue,
        ) {
            Icon(painter = painterResource(id = dev.mrjafari.weatherapp.R.drawable.ic_baseline_add_24), contentDescription ="", tint = white )
            Log.i("45656465", "")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun noteList( tes : ArrayList<String>) {

 /*   val tes = arrayListOf<String>("mohammdfdsfdfsdfdsfsfsdfxczxadreza ", "Alireza ", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli", "gogoli")
    tes.add("sfd")*/
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        /*  Text(
              text = "Country code: ${selectedValue.value.ifEmpty { "NONE" }}",
              modifier = Modifier.offset(20.dp)
          )*/
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 110.dp),
            modifier = Modifier
                .offset(0.dp, 0.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(
               start= 0.dp, top =  0.dp, end = 0.dp, bottom =  0.dp
            )
        ) {
            items(items = tes) {
                noteListItem(text = it.toString())
            }
        }
    }
}

@Composable
fun noteListItem(text: String) {

    Card(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .height(120.dp)
        .aspectRatio(1f),
        backgroundColor = cardColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                modifier = Modifier
                    .size(35.dp),
                painter = rememberImagePainter(data= "https://cdn-icons-png.flaticon.com/512/5532/5532989.png",
                    builder = {

                    }),
                contentDescription = "content discription"
            )
            Text(text = text, modifier = Modifier.padding(10.dp))

        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun searchBox(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    ContryName: MutableState<String>,
    Cityname :String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .coloredShadow(shadow, 0.5f, 0.dp, 9.dp, 4.dp, 0.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.2f)
                    .clickable {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            else
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                        /*      Toast.makeText(context
                              ,
                              "Showing toast....",
                              Toast.LENGTH_SHORT
                          ).show()
*/
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                Image(
                    modifier = Modifier.padding(15.dp, 15.dp, 5.dp, 15.dp),
                    painter = painterResource(dev.mrjafari.weatherapp.R.drawable.place),
                    contentDescription = "content discription"
                )
                Text(
                    text = ContryName.value,
                    fontSize = 16.sp,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
                    .padding(5.dp, 10.dp, 5.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Divider(
                    color = shadow,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Text(
                    text = Cityname,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp, 0.dp),
                    fontFamily = fonts,
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    // ExampleBox(shape = CircleShape)
                    Image(
                        painter = painterResource(dev.mrjafari.weatherapp.R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )
                }

            }

        }
    }
}

@Composable
fun todayStatus(date:String , weatherStatus :String , temp :Float,icon :String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .offset(20.dp, 20.dp)
        ) {
            Text(
                text = weatherStatus,
                fontSize = 25.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = date,
                fontSize = 23.sp,
                color = Color.Black,
                fontFamily = fonts,
                fontWeight = FontWeight.Light
            )



        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
                .offset(20.dp, 20.dp),
        )
        {
            val bitmap = ImageBitmap.imageResource(id = dev.mrjafari.weatherapp.R.drawable.sun)

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = blue)
                        .offset()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$temp °c",
                        fontSize = 22.sp,
                        color = white,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                modifier = Modifier
                    .size(105.dp),
                painter = rememberImagePainter(data= "https://www.weatherbit.io/static/img/icons/$icon.png",
                builder = {

                }),
                contentDescription = "content discription"
            )


        }

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



