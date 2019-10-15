package com.zaib.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zaib.baserepos.BaseRepository
import com.zaib.networking.RetrofitBuilder
import com.zaib.projectutils.ProjectConstants
import com.zaib.responsemodel.CityForeCast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Response


object MainActivityRepo : BaseRepository() {

    private var mutableLiveData = MutableLiveData<CityForeCast>()
    private var job: Job? = null


    fun getDailyForeCastForFiveDays(cityName: String): MutableLiveData<CityForeCast> {

        job = CoroutineScope(IO).launch {

            val listResponse =
                RetrofitBuilder.getCorService()
                    .getLocationsListAsync(cityName, ProjectConstants.appId).await()
            try {
                if (listResponse.isSuccessful) {


                    val cityForeCastResponse: Response<CityForeCast> =
                        RetrofitBuilder.getCorService().getDailyForeCastForFiveDaysAsync(
                            listResponse.body()?.get(0)?.Rank!!,
                            ProjectConstants.appId
                        ).await()


                    try {
                        if (cityForeCastResponse.isSuccessful) {
                            withContext(Dispatchers.Main)
                            {
                                mutableLiveData.value = cityForeCastResponse.body()
                            }
                        } else
                        {
                            Log.d("MainActivity ", cityForeCastResponse.errorBody().toString())
                        }

                    } catch (e: Exception) {

                    }


                } else {
                    Log.d("MainActivity ", listResponse.errorBody().toString())
                }

            } catch (e: Exception) {

            }


        }
        return mutableLiveData
    }

//
//        fun getDailyForeCastForFiveDays(cityName: String): MutableLiveData<CityForeCast> {
//
//
//            job = CoroutineScope(IO).launch {
//
//
//                val list = safeApiCall(
//                    call = {
//                        RetrofitBuilder.getCorService()
//                            .getLocationsListAsync(cityName, ProjectConstants.appId).await()
//                    },
//                    errorMessage = "Error Fetching"
//                )
//
//                if (!list.isNullOrEmpty()) {
//                    val cityForeCast = safeApiCall(
//                        call = {
//                            RetrofitBuilder.getCorService().getDailyForeCastForFiveDaysAsync(
//                                list[0].Rank,
//                                ProjectConstants.appId
//                            ).await()
//                        },
//                        errorMessage = "Error Fetching"
//                    )
//                    withContext(Main)
//                    {
//                        mutableLiveData.value = cityForeCast
//                    }
//                } else {
//                    ProgressDialogBox.ShowDismissDialog(false)
//
//                }
//            }
//            return mutableLiveData
//        }
//
//        fun cancelJob() {
//            job?.cancel()
//        }


    fun cancelJob() {
        job?.cancel()
    }


}
