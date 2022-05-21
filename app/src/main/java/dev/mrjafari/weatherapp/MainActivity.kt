package dev.mrjafari.weatherapp

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.presenter.MainPresenter
import dev.mrjafari.weatherapp.ui.theme.*
import dev.mrjafari.weatherapp.view.MainLayout
import dev.mrjafari.weatherapp.view.SearchBox
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Contract

class MainActivity : ComponentActivity(), MainContract.View {
    val state = mutableStateOf(true)
    var text = ""
    val Countries = mutableListOf<CountryModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme() {
                bottemsheet(Countries)
            }


        }

        val presenter = MainPresenter(this, this)
        presenter.request_data()
        presenter.CountryRequest()
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun setData(text: String) {
        state.value = true
        this.text = text
    }

    override fun onResponseFailure(throwable: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun getcountry(countryList: List<CountryModel>) {
        Countries.addAll(countryList)
    }


}


@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.coloredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = composed {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha = 0f).toArgb()

    this.drawBehind {

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun bottemsheet(countries: MutableList<CountryModel>) {
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

                    CountrySelected = Preview_MultipleRadioButtons(SearchResultCountry)


                }


            }
        }, sheetPeekHeight = 0.dp,

    ) {
        MainLayout(coroutineScope, bottomSheetScaffoldState, ContryName)

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
        /*  Text(
              text = "Country code: ${selectedValue.value.ifEmpty { "NONE" }}",
              modifier = Modifier.offset(20.dp)
          )*/
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

