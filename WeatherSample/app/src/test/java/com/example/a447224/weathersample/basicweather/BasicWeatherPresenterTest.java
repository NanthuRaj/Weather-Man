package com.example.a447224.weathersample.basicweather;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BasicWeatherPresenterTest {

    @Test
    public void checkProgressVisibilityOnWeatherCall(){
        IBasicWeatherView view = mock(IBasicWeatherView.class);
        BasicWeatherPresenter presenter = new BasicWeatherPresenter(view);
        presenter.fetchCurrentWeather("Chennai");
        verify(view).onWeatherUpdateStart();
    }
    @Test
    public void checkProgressVisibilityOnForecastCall(){
        IBasicWeatherView view = mock(IBasicWeatherView.class);
        BasicWeatherPresenter presenter = new BasicWeatherPresenter(view);
        presenter.fetchWeatherForecast("Chennai");
        verify(view).onWeatherUpdateStart();
    }
    @Test
    public void checkWeatherCallSuccess(){
        IBasicWeatherView view = mock(IBasicWeatherView.class);
        BasicWeatherPresenter presenter = new BasicWeatherPresenter(view);
        presenter.fetchWeatherForecast("Chennai");
        verify(view).onWeatherUpdateStart();
        verify(view).onWeatherUpdateCompleted();
    }

}