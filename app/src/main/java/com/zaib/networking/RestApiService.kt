package com.zaib.networking

import com.zaib.responsemodel.CityForeCast
import com.zaib.responsemodel.cityinfomodel.CityInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApiService
{

    @GET("/forecasts/v1/daily/5day/{q}")
    suspend fun getDailyForeCastForFiveDays(
        @Path("q") cityId: Int,
        @Query("apikey") appID: String
    ): CityForeCast



    @GET("locations/v1/cities/autocomplete")
    suspend fun getCitiesInfoList(
        @Query("q") cityName: String,
        @Query("apikey") appID: String
    ): ArrayList<CityInfo>

}