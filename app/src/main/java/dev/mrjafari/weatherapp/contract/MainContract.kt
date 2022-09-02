package dev.mrjafari.weatherapp.contract

import android.content.Context
import android.graphics.Movie
import dev.mrjafari.weatherapp.model.CountryModel
import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel


public interface MainContract {

    interface model{
        interface onFinishedListener{
            fun onFailed()
            fun onFinished(value: ResponseModel)
        }
        interface onsetCountryListener{

            fun onFinishSetCountry(CountryList :List<CountryModel>)
        }
        fun getData(onfinish :onFinishedListener, requestModel: RequestModel)
        fun getCountries(onfinish:onsetCountryListener , context :Context)
    }


    interface View {
        fun showProgress()
        fun hideProgress()
        fun setData(value:ResponseModel)
        fun onResponseFailure(throwable: Throwable?)
        fun getcountry(countryList :List<CountryModel>)

    }

    interface presenter{
        fun onDestory()
        fun request_data(requestModel: RequestModel)
        fun CountryRequest()
    }
}