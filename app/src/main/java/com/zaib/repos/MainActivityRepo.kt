package com.zaib.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaib.networking.RetrofitBuilder
import com.zaib.projectutils.ProjectConstants
import com.zaib.responsemodel.CityForeCast
import com.zaib.responsemodel.CityInfo.CitiesInfoList
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


object MainActivityRepo {

    var job: CompletableJob? = null
    private var mutableLiveData = MutableLiveData<CityForeCast>()

    //   suspend fun getDailyForeCastForFiveDays(cityId: Long): LiveData<CityForeCast> {
    //      job = Job()

//        return object : LiveData<CityForeCast>() {
//            override fun onActive() {
//                super.onActive()
//
//                job?.let { theJob ->
//                    CoroutineScope.(IO + theJob).launch {
//                        val cityForeCast = RetrofitBuilder.getCorService()
//                            .getDailyForeCastForFiveDays(
//                                cityId,
//                                ProjectConstants.appId
//                            )
//
//                        withContext(Main)
//                        {
//                            value = cityForeCast
//                            theJob.complete()
//                        }
//                    }
//                }
//            }
//        }

    //}


    fun getDailyForeCastForFiveDays(cityName: String): MutableLiveData<CityForeCast> {

        //  job = Job()

        //job?.let { theJob ->
        CoroutineScope(IO /*+ theJob*/).launch {

            var result1: Int = 0
            val job = launch {
                // println("debug: launching job1: ${Thread.currentThread().name}")
                val citiesInfoList = RetrofitBuilder.getCorService()
                    .getCitiesInfoList(cityName, ProjectConstants.appId)
                result1 = citiesInfoList.Rank

            }
            job.join()

            val result2 = async {
                println("debug: launching job2: ${Thread.currentThread().name}")
                val cityForeCast = RetrofitBuilder.getCorService()
                    .getDailyForeCastForFiveDays(
                        result1,
                        ProjectConstants.appId
                    )

                withContext(Main)
                {
                    mutableLiveData.value = cityForeCast
                }
            }.await()
            println("Got result2: $result2")

        }
        // }


        return mutableLiveData
    }

    fun cancelJob() {
        job?.cancel()
    }

}