package com.outdeved.alure.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.outdeved.alure.model.weather.ForecastDay;

import com.outdeved.alure.model.weather.Weather;

public class TestUtil {

    public static String loadJsonString(String path) throws IOException {
        InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(path);
        if (in == null) {
            throw new IllegalArgumentException("File not found: " + path);
        }
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }

    public static Weather loadWeatherJson(String path) {
        InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(path);
        JsonReader jsonReader = new JsonReader(new InputStreamReader(in));
        return GsonUtil.GSON.fromJson(jsonReader, Weather.class);
    }

    public static List<ForecastDay> loadPastWeatherJson(String path) {
        InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(path);
        JsonReader jsonReader = new JsonReader(new InputStreamReader(in));

        JsonObject json = GsonUtil.GSON.fromJson(jsonReader, JsonObject.class);
        JsonArray forecastDays = json.get("forecast").getAsJsonObject().get("forecastday").getAsJsonArray();

        return GsonUtil.GSON.fromJson(forecastDays, new TypeToken<List<ForecastDay>>() {
        }.getType());
    }
}
