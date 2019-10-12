package com.zaib.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaib.viewmodel.MainActivityViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.zaib.adapter.ItemCityWeatherAdapter
import com.zaib.views.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null

    private var mBinding: ActivityMainBinding? = null

    private var itemCityWeatherAdapter: ItemCityWeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        mBinding?.viewDailyWeather?.layoutManager = LinearLayoutManager(this)
        mBinding?.viewDailyWeather?.setHasFixedSize(true)

        itemCityWeatherAdapter = ItemCityWeatherAdapter(this)
        mBinding?.viewDailyWeather?.adapter = itemCityWeatherAdapter


        viewModel?.getCityForeCast?.observe(this, Observer {

            Log.v("DEBUG ZAIB: $it", it.toString())

            itemCityWeatherAdapter?.setForeCastDataList(it.DailyForecasts)

        })

        viewModel?.setCityName("Lahore")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.cancelJob()
    }
}
