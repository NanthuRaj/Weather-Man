package com.example.a447224.weathersample.detailedweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a447224.weathersample.R;
import com.example.a447224.weathersample.basicweather.IBasicWeatherView;
import com.example.a447224.weathersample.model.WeatherDetails;
import com.example.a447224.weathersample.model.WeatherInfo;
import com.example.a447224.weathersample.utils.Constants;
import com.example.a447224.weathersample.utils.Utils;

import java.util.List;

public class DetailedWeatherActivity extends AppCompatActivity implements IDetailedWeatherView {
    private DetailedWeatherPresenter presenter;
    private TextView mTvCity,mTvToday,mTvWeatherType,mTvTemp,mTvTempMinMax,mTvPressure,mTvHumidity,mTvWindDirection,mTvWindSpeed;
    private ImageView mIvWeatherType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        setPresenter();
        initializeViews();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if(getIntent() != null && getIntent().getSerializableExtra(Constants.CURRENT_WEATHER) != null){
          presenter.updateWeather((WeatherInfo) getIntent().getSerializableExtra(Constants.CURRENT_WEATHER));
        }
    }

    @Override
    public void setPresenter() {
        presenter = new DetailedWeatherPresenter(this);
    }

    @Override
    public void initializeViews() {
        mTvCity = findViewById(R.id.tvCity);
        mTvToday = findViewById(R.id.tvToday);
        mTvWeatherType = findViewById(R.id.tvWeatherType);
        mTvTemp = findViewById(R.id.tvTemp);
        mTvTempMinMax = findViewById(R.id.tvTempMinMax);
        mTvPressure = findViewById(R.id.tvPressure);
        mTvHumidity = findViewById(R.id.tvHumidity);
        mTvWindDirection = findViewById(R.id.tvWindDirection);
        mTvWindSpeed = findViewById(R.id.tvWindSpeed);
        mIvWeatherType = findViewById(R.id.ivWeatherType);
    }

    @Override
    public void updateWeather(WeatherInfo weatherInfo) {
        mTvCity.setText(Constants.CHENNAI);
        mTvToday.setText(Utils.convertTimeInMilliSecsTODateTime(System.currentTimeMillis()));
        if(weatherInfo.getWeather() != null && !weatherInfo.getWeather().isEmpty()
                && weatherInfo.getWeather().get(0) != null && !TextUtils.isEmpty(weatherInfo.getWeather().get(0).getDescription())){
            mTvWeatherType.setText(weatherInfo.getWeather().get(0).getDescription());
            setWeatherIcon(weatherInfo.getWeather().get(0).getDescription());
        }
        if(weatherInfo.getMain() != null ){
            mTvTemp.setText(Utils.convertKelvinToDegree(weatherInfo.getMain().getTemp()));
            mTvTempMinMax.setText(Utils.convertKelvinToDegree(weatherInfo.getMain().getTemp_min())+" / "+Utils.convertKelvinToDegree(weatherInfo.getMain().getTemp_max()));
            mTvPressure.setText("Pressure : "+String.valueOf(weatherInfo.getMain().getPressure())+"hpa");
            mTvHumidity.setText("Humidity : "+String.valueOf(weatherInfo.getMain().getHumidity())+"%");
        }
        if(weatherInfo.getWind() != null){
            mTvWindSpeed.setText("Speed : "+String.valueOf(weatherInfo.getWind().getSpeed())+"meter/sec");
            mTvWindDirection.setText("Direction : "+String.valueOf(weatherInfo.getWind().getDeg())+"deg");
        }
    }

    private void setWeatherIcon(String weatherType) {
        if (weatherType.contains(Constants.RAIN)) {
            mIvWeatherType.setImageResource(R.drawable.art_rain);
        } else if (weatherType.contains(Constants.CLEAR)) {
            mIvWeatherType.setImageResource(R.drawable.art_clear);
        } else if (weatherType.contains(Constants.CLOUDS)) {
            mIvWeatherType.setImageResource(R.drawable.art_clouds);
        } else if (weatherType.contains(Constants.SNOW)) {
            mIvWeatherType.setImageResource(R.drawable.art_snow);
        } else if (weatherType.contains(Constants.EXTREME)) {
            mIvWeatherType.setImageResource(R.drawable.art_fog);
        } else {
            mIvWeatherType.setImageResource(R.drawable.art_clear);
        }
    }

}
