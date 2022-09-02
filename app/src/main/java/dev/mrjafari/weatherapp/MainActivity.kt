package dev.mrjafari.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.ViewDataModel
import dev.mrjafari.weatherapp.model.remote.PostService
import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.Data
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.Weather
import dev.mrjafari.weatherapp.presenter.MainPresenter
import dev.mrjafari.weatherapp.ui.theme.*
import dev.mrjafari.weatherapp.view.MainLayout
import dev.mrjafari.weatherapp.view.Navigation
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Contract
class MainActivity : ComponentActivity(), MainContract.View {
    val presenter = MainPresenter(this, this)

    var data = mutableStateOf(ResponseModel)
    var a= mutableStateOf(ViewDataModel("","","",""))
    val test = mutableStateOf("ali")
    var text = ""
    val Countries = mutableListOf<CountryModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

                   // Navigation(Countries,data!!.value)
                Text(text = test.value , fontSize = 45.sp , modifier = Modifier.clickable {
                    test.value = "asgar"
                    val model = RequestModel("US","newyork")

                    presenter.request_data(model)
                    
                })


        }

        val model = RequestModel("IR","mashhad")

       presenter.request_data(model)
        presenter.CountryRequest()

    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun setData(value: ResponseModel) {
       data.value  = value
        test.value = value.data[0].city_name
        Log.i("51544545",value.data[0].country_code)
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


