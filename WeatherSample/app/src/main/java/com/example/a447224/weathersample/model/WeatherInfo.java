package com.example.a447224.weathersample.model;

import java.io.Serializable;
import java.util.List;

public class WeatherInfo implements Serializable {

    private long id;
    private String name;
    private int cod;
    private List<Weather> weather;
    private String base;
    private long dt;
    private Clouds clouds;
    private Coord coord;
    private Main main;
    private Sys sys;
    private Wind wind;
    private int visibility;
    private String dt_txt;

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds Clouds) {
        this.clouds = Clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord Coord) {
        this.coord = Coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main Main) {
        this.main = Main;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys Sys) {
        this.sys = Sys;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind Wind) {
        this.wind = Wind;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }


}
