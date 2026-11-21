package com.example.assignment1_dorothyowususarpong.model;

import java.io.Serializable;

public class WeatherData implements Serializable {

    private String locationName;
    private String region;
    private double tempC;
    private double tempF;
    private String conditionText;
    private double windKph;
    private double feelsLikeC;
    private double feelsLikeF;

    public WeatherData() {
    }

    public WeatherData(String locationName, String region, double tempC, double tempF,
                       String conditionText, double windKph, double feelsLikeC, double feelsLikeF) {
        this.locationName = locationName;
        this.region = region;
        this.tempC = tempC;
        this.tempF = tempF;
        this.conditionText = conditionText;
        this.windKph = windKph;
        this.feelsLikeC = feelsLikeC;
        this.feelsLikeF = feelsLikeF;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }

    public double getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(double feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public double getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(double feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }
}
