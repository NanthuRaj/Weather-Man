package com.example.a447224.weathersample.basicweather;

import com.example.a447224.weathersample.dataprovider.APIClient;
import com.example.a447224.weathersample.dataprovider.ServiceInterface;
import com.example.a447224.weathersample.model.WeatherDetails;
import com.example.a447224.weathersample.model.WeatherForecast;
import com.example.a447224.weathersample.model.WeatherInfo;
import com.example.a447224.weathersample.utils.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BasicWeatherPresenter implements IBasicWeatherPresenter {
    private IBasicWeatherView view;

    public BasicWeatherPresenter(IBasicWeatherView view) {
        this.view = view;
    }

    @Override
    public void fetchCurrentWeather(String cityName) {
        APIClient apiClient = new APIClient();
        Retrofit retrofit = apiClient.getAPIClient();
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<WeatherInfo> weatherInfoCall = serviceInterface.getWeatherByCityName(Constants.CHENNAI, Constants.APP_ID);
        view.onWeatherUpdateStart();
        weatherInfoCall.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                view.onWeatherUpdateCompleted();
                if(response != null && response.isSuccessful() && response.body() != null){
                    view.updateCurrentWeather(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                view.onWeatherUpdateFailed(t.getMessage());
            }
        });
    }

    @Override
    public void fetchWeatherForecast(String cityName) {
        APIClient apiClient = new APIClient();
        Retrofit retrofit = apiClient.getAPIClient();
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<WeatherForecast> weatherForecastCall = serviceInterface.getWeatherForecastByCityName(Constants.CHENNAI, Constants.APP_ID,Constants.DAYS_FORECAST);
        view.onWeatherUpdateStart();
        weatherForecastCall.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                view.onWeatherUpdateCompleted();
                if(response != null && response.isSuccessful() && response.body() != null){
                    WeatherForecast weatherForecast = response.body();
                    if(weatherForecast != null && weatherForecast.getList() != null && !weatherForecast.getList().isEmpty()){
                        view.updateForecast(updateList(weatherForecast.getList()));
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                view.onWeatherUpdateFailed(t.getMessage());
            }
        });
    }

    private List<WeatherDetails> updateList(List<WeatherDetails> weatherList) {
          if(weatherList.size() == 4){
              weatherList.remove(0);
          }
            Calendar calendar = Calendar.getInstance();
            Date today = new Date();
            calendar.setTime(today);
          for(int i = 0;i<weatherList.size();i++){
              calendar.add(Calendar.DATE,1);
              weatherList.get(i).setDate(calendar.getTimeInMillis());
          }
        return weatherList;
    }
}
