package dev.mrjafari.weatherapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mrjafari.weatherapp.coloredShadow
import dev.mrjafari.weatherapp.ui.theme.*
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainLayout(
    coroutine: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    ContryName: MutableState<String>
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

            SearchBox(coroutine, bottomSheetScaffoldState, ContryName)
            todayStatus()
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBox(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    ContryName: MutableState<String>
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
                Text(text = ContryName.value, fontSize = 16.sp,fontFamily = fonts, fontWeight = FontWeight.Bold)
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
                Text(text = "tehran", fontSize = 18.sp, modifier = Modifier.padding(8.dp, 0.dp),fontFamily = fonts, fontWeight = FontWeight.Normal)
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
fun todayStatus() {
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
            Text(text = "Tody, 12 jan", fontSize = 28.sp, color = Color.Black, fontFamily = fonts, fontWeight = FontWeight.Light)
            Text(text = "Snow shower", fontSize = 32.sp, fontFamily = fonts, fontWeight = FontWeight.Normal)


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
                    Text(text = "21°c", fontSize = 22.sp, color = white, fontFamily = fonts, fontWeight = FontWeight.Bold)
                }
            }
            Image(
                bitmap = bitmap,
                contentDescription = "",
                modifier = Modifier
                    .size(125.dp)

            )

        }

    }
}

