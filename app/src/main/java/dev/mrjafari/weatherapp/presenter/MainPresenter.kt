package dev.mrjafari.weatherapp.presenter

import dev.mrjafari.weatherapp.contract.MainContract
import dev.mrjafari.weatherapp.model.MainModel

class MainPresenter(val view : MainContract.View):MainContract.presenter , MainContract.model.onFinishedListener{
    val model : MainContract.model = MainModel()


    override fun onDestory() {
        TODO("Not yet implemented")
    }

    override fun request_data() {
        model.getData(this)
    }

    override fun onFailed() {
        TODO("Not yet implemented")
    }

    override fun onFinished(value :String) {
        view.setData(value)
    }
}