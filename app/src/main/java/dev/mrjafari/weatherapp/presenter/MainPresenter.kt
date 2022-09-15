package dev.mrjafari.weatherapp.presenter

import android.content.Context
import android.util.Log
import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.MainModel
import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel

class MainPresenter(val view : MainContract.View,val context :Context):MainContract.presenter , MainContract.model.onFinishedListener,MainContract.model.onsetCountryListener{
    val model : MainContract.model = MainModel()

    override fun onDestory() {
        TODO("Not yet implemented")
    }

    override fun request_data(requestModel: RequestModel) {
        view.showProgress()
        Log.i("56456464","rec")
        model.getData(this,requestModel)


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

    override fun onFinished(value : ResponseModel) {
        view.hideProgress()
        view.setData(value)

        Log.i("56456464","onfinished")
    }
}