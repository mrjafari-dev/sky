package dev.mrjafari.weatherapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.remote.PostService
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import androidx.compose.runtime.produceState
import io.ktor.client.engine.android.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class MainModel:MainContract.model {
    private val service =    PostService.create()


    override fun getData(onfinish: MainContract.model.onFinishedListener) {
            onfinish.onFinished(a())


    }

    fun a()= runBlocking {
        delay(2000)
        getData()
    }
    override fun getCountries(onfinish: MainContract.model.onsetCountryListener, context: Context) {
        onfinish.onFinishSetCountry(getCountryLists(context))

    }


    suspend fun getData():ResponseModel{
      val data =  service.getPosts()

    return data;
}

    fun getCountryFromJson(context :Context,FileName :String):String{
        val json:String
        json =context.assets.open(FileName).bufferedReader().use { it.readText() }
        return json
    }
    fun getCountryLists(context: Context):List<CountryModel>{
        val jsonFileToString = getCountryFromJson(context , "country.json")
        val gson =Gson()
        val listCountryType=object :TypeToken<List<CountryModel>>(){}.type
        val countrys:List<CountryModel> = gson.fromJson(jsonFileToString,listCountryType)
        return countrys
    }
}