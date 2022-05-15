package dev.mrjafari.weatherapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.mrjafari.weatherapp.contract.MainContract

class MainModel:MainContract.model {

    override fun getData(onfinish: MainContract.model.onFinishedListener) {
        if (getData()) onfinish.onFinished("server value") else onfinish.onFailed()
    }

    override fun getCountries(onfinish: MainContract.model.onsetCountryListener, context: Context) {
        onfinish.onFinishSetCountry(getCountryLists(context))

    }


    fun getData():Boolean{
    return true;
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