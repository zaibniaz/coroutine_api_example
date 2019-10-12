package com.zaib.networking

import com.zaib.responsemodel.CityForeCast
import com.zaib.responsemodel.CityInfo.CitiesInfoList
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiService
{

    @GET("/daily/5day/{q}/{apikey}")
    suspend fun getDailyForeCastForFiveDays(
        @Path("q") cityId: Int,
        @Path("apikey") appID: String
    ): CityForeCast



    @GET("/locations/v1/cities/autocomplete/{q}/{apikey}")
    suspend fun getCitiesInfoList(
        @Path("q") cityName: String,
        @Path("apikey") appID: String
    ): CitiesInfoList

}