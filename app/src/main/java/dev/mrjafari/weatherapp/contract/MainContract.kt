package dev.mrjafari.weatherapp.contract

import android.graphics.Movie




public interface MainContract {

    interface model{
        interface onFinishedListener{
            fun onFailed()
            fun onFinished(value:String)
        }
        fun getData(onfinish :onFinishedListener)
    }
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setData(text:String)
        fun onResponseFailure(throwable: Throwable?)
    }
    interface presenter{
        fun onDestory()
        fun request_data()
    }
}