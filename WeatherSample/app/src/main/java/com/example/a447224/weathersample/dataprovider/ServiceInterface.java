package com.example.a447224.weathersample.dataprovider;

import com.example.a447224.weathersample.model.WeatherForecast;
import com.example.a447224.weathersample.model.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("weather")
    Call<WeatherInfo> getWeatherByCityName(@Query("q") String cityName, @Query("APPID") String appId);

    @GET("forecast/daily")
    Call<WeatherForecast> getWeatherForecastByCityName(@Query("q") String cityName, @Query("APPID") String appId,@Query("cnt") String daysCount);

}
