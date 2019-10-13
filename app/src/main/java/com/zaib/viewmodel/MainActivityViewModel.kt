package com.zaib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zaib.repos.MainActivityRepo
import com.zaib.responsemodel.CityForeCast

class MainActivityViewModel : ViewModel() {

    private val _cityName: MutableLiveData<String> = MutableLiveData()


    val getCityForeCast: LiveData<CityForeCast> = Transformations.switchMap(_cityName)
    {
        MainActivityRepo.getDailyForeCastForFiveDays(it)
    }

    fun setCityName(cityName: String) {
//        if (_cityName.value == null)
//            return

        _cityName.value = cityName
    }


    fun cancelJob() {
        MainActivityRepo.cancelJob()
    }
}