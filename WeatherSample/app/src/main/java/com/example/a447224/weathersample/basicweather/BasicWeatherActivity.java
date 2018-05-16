package com.example.a447224.weathersample.basicweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a447224.weathersample.R;
import com.example.a447224.weathersample.basicweather.adapter.ForecastAdapter;
import com.example.a447224.weathersample.detailedweather.DetailedWeatherActivity;
import com.example.a447224.weathersample.model.WeatherDetails;
import com.example.a447224.weathersample.model.WeatherInfo;
import com.example.a447224.weathersample.utils.Constants;
import com.example.a447224.weathersample.utils.Utils;

import java.util.List;

public class BasicWeatherActivity extends AppCompatActivity implements IBasicWeatherView,View.OnClickListener {
    private BasicWeatherPresenter mPresenter;
    private TextView mTvCity,mTvToday,mTvWeatherType,mTvTemp,mTvTempMinMax;
    private ImageView mIvWeatherType;
    private CardView mCvCurrentWeather;
    private RecyclerView mRvForecast;
    private ForecastAdapter forecastAdapter;
    private ProgressDialog mProgressDialog;
    private WeatherInfo mCurrentWeatherInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        setPresenter();
        initializeViews();
        mPresenter.fetchCurrentWeather(Constants.CHENNAI);
        mPresenter.fetchWeatherForecast(Constants.CHENNAI);
    }

    @Override
    public void setPresenter() {
        mPresenter = new BasicWeatherPresenter(this);
    }

    @Override
    public void initializeViews() {
        mCvCurrentWeather = findViewById(R.id.cvCurrentWeather);
        mCvCurrentWeather.setOnClickListener(this);
        mTvCity = findViewById(R.id.tvCity);
        mTvToday = findViewById(R.id.tvToday);
        mTvWeatherType = findViewById(R.id.tvWeatherType);
        mTvTemp = findViewById(R.id.tvTemp);
        mTvTempMinMax = findViewById(R.id.tvTempMinMax);
        mIvWeatherType = findViewById(R.id.ivWeatherType);
        mRvForecast = findViewById(R.id.rvForecast);
        mRvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        forecastAdapter = new ForecastAdapter(this);
        mRvForecast.setAdapter(forecastAdapter);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.weather_update_message));

    }

    @Override
    public void updateCurrentWeather(WeatherInfo weatherInfo) {
        mCurrentWeatherInfo = weatherInfo;
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
        }

    }

    @Override
    public void updateForecast(List<WeatherDetails> weatherForecastList) {
        forecastAdapter.updateForecast(weatherForecastList);
        forecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void onWeatherUpdateStart() {
        if(mProgressDialog != null){
            mProgressDialog.show();
        }
    }

    @Override
    public void onWeatherUpdateCompleted() {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onWeatherUpdateFailed(String errorMessage) {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
        Snackbar.make(mRvForecast,errorMessage,Snackbar.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cvCurrentWeather){
            showDetailedWeather();
        }
    }

    private void showDetailedWeather() {
        if(mCurrentWeatherInfo != null){
            Intent weatherIntent = new Intent(BasicWeatherActivity.this, DetailedWeatherActivity.class);
            weatherIntent.putExtra(Constants.CURRENT_WEATHER,mCurrentWeatherInfo);
            startActivity(weatherIntent);
        }
    }
}
