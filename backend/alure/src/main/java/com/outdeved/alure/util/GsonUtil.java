package com.outdeved.alure.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.outdeved.alure.model.weather.ForecastDay;
import com.outdeved.alure.model.weather.Weather;

public class GsonUtil {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Weather.class, new Weather.WeatherDeserializer())
            .registerTypeAdapter(ForecastDay.class, new ForecastDay.ForecastDayDeserializer())
            .create();
}
