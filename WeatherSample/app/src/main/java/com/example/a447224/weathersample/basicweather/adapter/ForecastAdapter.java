package com.example.a447224.weathersample.basicweather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a447224.weathersample.R;
import com.example.a447224.weathersample.model.WeatherDetails;
import com.example.a447224.weathersample.utils.Constants;
import com.example.a447224.weathersample.utils.Utils;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{
    private Context mContext;
    private List<WeatherDetails> mWeatherDetailsList;

    public ForecastAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void updateForecast(List<WeatherDetails> weatherDetailsList){
        mWeatherDetailsList = weatherDetailsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_forecast_row,parent,false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        if(mWeatherDetailsList.get(position) != null){
            WeatherDetails weatherDetails = mWeatherDetailsList.get(position);
            holder.tvDate.setText(Utils.convertTimeInMilliSecsTODateTime(weatherDetails.getDate()));
            if(weatherDetails.getWeather() != null && !weatherDetails.getWeather().isEmpty() && weatherDetails.getWeather().get(0) != null
                    && !TextUtils.isEmpty(weatherDetails.getWeather().get(0).getMain())){
                holder.tvWeatherType.setText(weatherDetails.getWeather().get(0).getMain());
                setWeatherIcon(holder.ivWeatherType,weatherDetails.getWeather().get(0).getMain());
            }
            if(weatherDetails.getTemp() != null){
                holder.tvTempMinMax.setText(Utils.convertKelvinToDegree(weatherDetails.getTemp().getMin())+" / "+Utils.convertKelvinToDegree(weatherDetails.getTemp().getMax()));
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mWeatherDetailsList != null){
            return mWeatherDetailsList.size();
        }
        return 0;
    }
    private void setWeatherIcon(ImageView ivWeatherType,String weatherType) {
        if (weatherType.contains(Constants.RAIN)) {
            ivWeatherType.setImageResource(R.drawable.art_rain);
        } else if (weatherType.contains(Constants.CLEAR)) {
            ivWeatherType.setImageResource(R.drawable.art_clear);
        } else if (weatherType.contains(Constants.CLOUDS)) {
            ivWeatherType.setImageResource(R.drawable.art_clouds);
        } else if (weatherType.contains(Constants.SNOW)) {
            ivWeatherType.setImageResource(R.drawable.art_snow);
        } else if (weatherType.contains(Constants.EXTREME)) {
            ivWeatherType.setImageResource(R.drawable.art_fog);
        } else {
            ivWeatherType.setImageResource(R.drawable.art_clear);
        }
    }
    public class ForecastViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDate,tvWeatherType,tvTempMinMax;
        private ImageView ivWeatherType;
        public ForecastViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvWeatherType = itemView.findViewById(R.id.tvWeatherType);
            tvTempMinMax = itemView.findViewById(R.id.tvTempMinMax);
            ivWeatherType = itemView.findViewById(R.id.ivWeatherType);
        }
    }
}
