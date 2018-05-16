package com.example.a447224.weathersample.model;


import java.io.Serializable;

public class Coord implements Serializable {
    private double lat;
    private double lon;
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


}
