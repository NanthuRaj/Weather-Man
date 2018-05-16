package com.example.a447224.weathersample.basicweather;

import com.example.a447224.weathersample.model.WeatherDetails;
import com.example.a447224.weathersample.model.WeatherInfo;

import java.util.List;

public interface IBasicWeatherView {
    void setPresenter();
    void initializeViews();
    void updateCurrentWeather(WeatherInfo weatherInfo);
    void updateForecast(List<WeatherDetails> weatherForecastList);
    void onWeatherUpdateStart();
    void onWeatherUpdateCompleted();
    void onWeatherUpdateFailed(String errorMessage);
}
