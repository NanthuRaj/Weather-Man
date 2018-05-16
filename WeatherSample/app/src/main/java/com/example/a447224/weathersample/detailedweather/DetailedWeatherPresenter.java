package com.example.a447224.weathersample.detailedweather;

import com.example.a447224.weathersample.model.WeatherInfo;

public class DetailedWeatherPresenter implements IDetailedWeatherPresenter {
    private IDetailedWeatherView view;

    public DetailedWeatherPresenter(IDetailedWeatherView view) {
        this.view = view;
    }

    @Override
    public void updateWeather(WeatherInfo weatherInfo) {
        view.updateWeather(weatherInfo);
    }
}
