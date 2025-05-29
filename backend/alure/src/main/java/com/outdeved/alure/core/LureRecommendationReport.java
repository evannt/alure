package com.outdeved.alure.core;

import java.util.List;

import com.outdeved.alure.model.weather.Weather;

public class LureRecommendationReport {

    private String name;
    private String region;
    private String country;
    private double temp;
    private int estWaterTemp;
    private String condition;
    private double pressure;

    private List<LureRecommendation> lures;

    public LureRecommendationReport(Weather weather, int estWaterTemp, List<LureRecommendation> lures) {
        this.name = weather.getLocationName();
        this.region = weather.getLocationRegion();
        this.country = weather.getCountry();
        this.temp = weather.getTemperature();
        this.estWaterTemp = estWaterTemp;
        this.condition = weather.getWeatherCondition().name;
        this.pressure = weather.getPressure();
        this.lures = lures;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public int getEstWaterTemp() {
        return estWaterTemp;
    }

    public String getCondition() {
        return condition;
    }

    public double getPressure() {
        return pressure;
    }

    public List<LureRecommendation> getLures() {
        return lures;
    }

    @Override
    public String toString() {
        return "LureRecommendationReport={ locationName=" + name + ", locationRegion=" + region
                + ", country=" + country + ", temp=" + temp + ", condition=" + condition + ", pressure=" + pressure
                + ", lures=" + lures + " }";
    }
}
