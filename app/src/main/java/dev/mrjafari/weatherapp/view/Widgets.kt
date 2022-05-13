package dev.mrjafari.weatherapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mrjafari.weatherapp.coloredShadow
import dev.mrjafari.weatherapp.ui.theme.shadow
import dev.mrjafari.weatherapp.ui.theme.white
import kotlinx.coroutines.launch


@Composable
fun MainLayout(){
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        white,
                        white
                    )
                )
            ).fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(20.dp, 30.dp, 20.dp)) {

            SearchBox()

        }

    }
}


@Composable
fun SearchBox(){
    Card(
        modifier = Modifier.fillMaxWidth().height(55.dp)
            .coloredShadow(shadow, 0.5f, 0.dp, 9.dp, 4.dp, 0.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        val context = LocalContext.current
        Row(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.2f).clickable {
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
                    painter = painterResource(R.drawable.place),
                    contentDescription = "content discription"
                )
                Text(text = ContryName.value, fontSize = 22.sp)
            }
            Row(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.7f).padding(5.dp, 10.dp, 5.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Divider(
                    color = shadow,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Text(text = "tehran", fontSize = 18.sp, modifier = Modifier.padding(8.dp, 0.dp))
            }
            Row(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    ExampleBox(shape = CircleShape)
                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )
                }

            }

        }
    }
}


