package com.mvvm.weatherapp.di.module

import com.mvvm.weatherapp.BuildConfig
import com.mvvm.weatherapp.data.remote.CityApiService
import com.mvvm.weatherapp.data.remote.ForeCastApiService
import com.mvvm.weatherapp.data.repository.data_source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    @Named("cityApi")
    fun provideCityRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        adapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.CITY_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

    @Singleton
    @Provides
    @Named("forecastApi")
    fun provideForecastRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        adapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FORECAST_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideCityApiService(@Named("cityApi") retrofit: Retrofit): CityApiService {
        return retrofit.create(CityApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideForecastApiService(@Named("forecastApi") retrofit: Retrofit): ForeCastApiService {
        return retrofit.create(ForeCastApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        cityApiService: CityApiService,
        foreCastApiService: ForeCastApiService
    ): RemoteDataSource {
        return RemoteDataSource(
            cityApiService,
            foreCastApiService
        )
    }
}