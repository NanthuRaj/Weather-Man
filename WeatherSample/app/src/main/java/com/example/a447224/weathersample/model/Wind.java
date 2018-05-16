package com.example.a447224.weathersample.model;


import java.io.Serializable;

public class Wind implements Serializable {
    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
