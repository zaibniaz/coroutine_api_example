package com.zaib.networking

import com.zaib.responsemodel.CityForeCast
import com.zaib.responsemodel.cityinfomodel.CityInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApiService
{

    @GET("/forecasts/v1/daily/5day/{q}")
    fun getDailyForeCastForFiveDaysAsync(
        @Path("q") cityId: Int,
        @Query("apikey") appID: String
    ): Deferred<Response<CityForeCast>>



    @GET("locations/v1/cities/autocomplete")
    fun getLocationsListAsync(
        @Query("q") cityName: String,
        @Query("apikey") appID: String
    ): Deferred<Response<List<CityInfo>>>

}