package com.outdeved.alure.model.weather;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.outdeved.alure.util.GsonUtil;

public class Weather {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String locationName;
    private String locationRegion;
    private String country;
    private LocalDateTime datetime;
    private double temperature;
    private double windSpeed;
    private double pressure;
    private List<ForecastDay> forecastDays;

    public Weather(JsonObject json) {
        JsonObject locationJson = json.get("location").getAsJsonObject();
        this.locationName = locationJson.get("name").getAsString();
        this.locationRegion = locationJson.get("region").getAsString();
        this.country = locationJson.get("country").getAsString();
        this.datetime = LocalDateTime.parse(locationJson.get("localtime").getAsString(), DATE_TIME_FORMAT);

        JsonObject currentWeatherJson = json.get("current").getAsJsonObject();
        this.temperature = currentWeatherJson.get("temp_f").getAsDouble();
        this.windSpeed = currentWeatherJson.get("wind_mph").getAsDouble();
        this.pressure = currentWeatherJson.get("pressure_in").getAsDouble();

        JsonArray forecastJsonArray = json.get("forecast").getAsJsonObject().get("forecastday").getAsJsonArray();
        this.forecastDays = GsonUtil.GSON.fromJson(forecastJsonArray, new TypeToken<List<ForecastDay>>() {
        }.getType());
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getDateTime() {
        return datetime;
    }

    public Month getMonth() {
        return datetime.getMonth();
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public double getAvgTemp() {
        return getCurrentForecast().getAvgTemp();
    }

    public WeatherCondition getWeatherCondition() {
        return getCurrentForecast().getWeatherCondition();
    }

    public MoonPhase getMoonPhase() {
        return getCurrentForecast().getMoonPhase();
    }

    public int getMoonIllumination() {
        return getCurrentForecast().getMoonIllumination();
    }

    public List<ForecastDay> getForecastDays() {
        return forecastDays;
    }

    public ForecastDay getCurrentForecast() {
        return forecastDays.getFirst();
    }

    @Override
    public String toString() {
        return "Weather [ temperature=" + temperature + ", wwindSpeed=" + windSpeed + ", pressure=" + pressure
                + ", forecastDays=" + forecastDays + " ]";
    }

    public static class WeatherDeserializer implements JsonDeserializer<Weather> {

        @Override
        public Weather deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            return new Weather(json.getAsJsonObject());
        }
    }

}
