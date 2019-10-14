package com.zaib.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaib.viewmodel.MainActivityViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.zaib.adapter.ItemCityWeatherAdapter
import com.zaib.projectutils.ProgressDialogBox
import com.zaib.views.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener
     {


    private var viewModel: MainActivityViewModel? = null

    private var mBinding: ActivityMainBinding? = null

    private var itemCityWeatherAdapter: ItemCityWeatherAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ProgressDialogBox.setProgressBar(this@MainActivity)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        mBinding?.viewDailyWeather?.layoutManager = LinearLayoutManager(this)
        mBinding?.viewDailyWeather?.setHasFixedSize(true)

        itemCityWeatherAdapter = ItemCityWeatherAdapter(this)
        mBinding?.viewDailyWeather?.adapter = itemCityWeatherAdapter


      //  viewModel?.setCityName("Lahore")

        mBinding!!.searchView.setOnQueryTextListener(this)


        viewModel?.getCityForeCast?.observe(this, Observer {

           // Log.v("DEBUG ZAIB: $it", it.toString())
           ProgressDialogBox.ShowDismissDialog(false)

            itemCityWeatherAdapter?.setForeCastDataList(it.DailyForecasts)

        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       ProgressDialogBox.ShowDismissDialog(true)
        viewModel?.setCityName(query.toString())

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.cancelJob()
    }
}
