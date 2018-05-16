package com.example.a447224.weathersample.detailedweather;

import com.example.a447224.weathersample.model.WeatherInfo;

public interface IDetailedWeatherView {
    void setPresenter();
    void initializeViews();
    void updateWeather(WeatherInfo weatherInfo);
}
