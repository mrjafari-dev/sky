package dev.mrjafari.weatherapp.presenter

import android.content.Context
import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.MainModel

class MainPresenter(val view : MainContract.View,val context :Context):MainContract.presenter , MainContract.model.onFinishedListener,MainContract.model.onsetCountryListener{
    val model : MainContract.model = MainModel()

    override fun onDestory() {
        TODO("Not yet implemented")
    }

    override fun request_data() {
        model.getData(this)
    }

    override fun CountryRequest() {
        model.getCountries(this,context)
    }

    override fun onFailed() {
        TODO("Not yet implemented")
    }

    override fun onFinishSetCountry(CountryList: List<CountryModel>) {
        view.getcountry(CountryList)
    }

    override fun onFinished(value :String) {
        view.setData(value)
    }
}