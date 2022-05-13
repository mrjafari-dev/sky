package dev.mrjafari.weatherapp.model

import dev.mrjafari.weatherapp.contract.MainContract

class MainModel:MainContract.model {
    override fun getData(onfinish: MainContract.model.onFinishedListener) {
        if (getData()) onfinish.onFinished("server value") else onfinish.onFailed()
    }
fun getData():Boolean{
    return true;
}
}