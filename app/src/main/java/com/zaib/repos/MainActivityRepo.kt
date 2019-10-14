package com.zaib.repos

import androidx.lifecycle.MutableLiveData
import com.zaib.networking.RetrofitBuilder
import com.zaib.projectutils.ProgressDialogBox
import com.zaib.projectutils.ProjectConstants
import com.zaib.responsemodel.CityForeCast
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

        CoroutineScope(IO).launch {

            val cityIdList = withContext(CoroutineScope(IO).coroutineContext) {

                RetrofitBuilder.getCorService().getCitiesInfoList(cityName, ProjectConstants.appId)

            }
            if (cityIdList.isNotEmpty()) {


                val cityForeCast = withContext(CoroutineScope(IO).coroutineContext) {


                    RetrofitBuilder.getCorService()
                        .getDailyForeCastForFiveDays(
                            cityIdList[0].Rank,
                            ProjectConstants.appId
                        )
                }

                withContext(Main)
                {
                    mutableLiveData.value = cityForeCast
                }
            }
            else
                ProgressDialogBox.ShowDismissDialog(false)
        }
        return mutableLiveData
    }

    fun cancelJob() {
        job?.cancel()
    }

}