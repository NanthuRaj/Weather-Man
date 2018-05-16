package com.example.a447224.weathersample.basicweather;

public interface IBasicWeatherPresenter {
    void fetchCurrentWeather(String cityName);
    void fetchWeatherForecast(String cityName);
}
