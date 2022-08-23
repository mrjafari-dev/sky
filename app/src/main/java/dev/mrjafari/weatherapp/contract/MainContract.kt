package dev.mrjafari.weatherapp.contract

import android.content.Context
import android.graphics.Movie
import dev.mrjafari.weatherapp.model.CountryModel


public interface MainContract {

    interface model{
        interface onFinishedListener{
            fun onFailed()
            fun onFinished(value:String)
        }
        interface onsetCountryListener{

            fun onFinishSetCountry(CountryList :List<CountryModel>)
        }
        fun getData(onfinish :onFinishedListener)
        fun getCountries(onfinish:onsetCountryListener , context :Context)
    }


    interface View {
        fun showProgress()
        fun hideProgress()
        fun setData(text:String)
        fun onResponseFailure(throwable: Throwable?)
        fun getcountry(countryList :List<CountryModel>)

    }

    interface presenter{
        fun onDestory()
        fun request_data()
        fun CountryRequest()
    }
}