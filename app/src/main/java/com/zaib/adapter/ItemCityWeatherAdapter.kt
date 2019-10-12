package com.zaib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.zaib.responsemodel.CityForeCast
import com.zaib.responsemodel.DailyForecast
import com.zaib.views.R
import com.zaib.views.databinding.ItemCityWeatherLytBinding


class ItemCityWeatherAdapter(context: Context) :
    RecyclerView.Adapter<ItemCityWeatherAdapter.ForeCastViewHolder>() {

    val inflater = LayoutInflater.from(context)

    var foreCastList = ArrayList<DailyForecast>()


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val binding: ItemCityWeatherLytBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_city_weather_lyt, parent, false
        )
        return ForeCastViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        // holder.bindItems(userList[position])

        val city = foreCastList[position]
        holder.weatherCityListItemBinding.dailyForecast = city
    }

    fun setForeCastDataList(foreCastList: ArrayList<DailyForecast>) {
        this.foreCastList = foreCastList
        notifyDataSetChanged()
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return foreCastList.size
    }

    class ForeCastViewHolder(val weatherCityListItemBinding: ItemCityWeatherLytBinding) :
        RecyclerView.ViewHolder(weatherCityListItemBinding.root)
}