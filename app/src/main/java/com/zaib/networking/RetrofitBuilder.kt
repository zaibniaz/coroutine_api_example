package com.zaib.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor



class RetrofitBuilder
{

    companion object
    {
        private  val interceptor = HttpLoggingInterceptor()

        fun getCorService(): RestApiService {

            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder().connectTimeout(1,TimeUnit.MINUTES)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .writeTimeout(15,TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/locations/v1")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build().create(RestApiService::class.java)
        }


    }
}