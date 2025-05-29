package com.outdeved.alure.model.weather;

import java.util.HashMap;
import java.util.Map;

/**
 * WeatherCondition
 *
 * Weather conditions mapped to a centralized type.
 *
 * Conditions come from a weather API with extensive
 * weather condition types that were condensed to
 * reduce complexity and group conditions by their
 * primary weather type.
 */
public enum WeatherCondition {

    CLEAR("Clear"), // Sunny/Clear
    PARTLY_CLOUDY("Partly Cloudy"), // Partly cloudy
    CLOUDY("Cloudy"), // Cloudy
    OVERCAST("Overcast"), // Overcast
    FOGGY("Foggy"), // Mist, Fog, Freezing fog
    DRIZZLE("Drizzle"), // All drizzle variants
    LIGHT_RAIN("Light Rain"), // Patchy/light rain
    MODERATE_RAIN("Moderate Rain"), // Moderate rain
    HEAVY_RAIN("Heavy Rain"), // Heavy rain/showers
    LIGHT_SNOW("Light Snow"), // Patchy/light snow
    MODERATE_SNOW("Moderate Snow"), // Moderate snow
    HEAVY_SNOW("Heavy Snow"), // Heavy snow/blizzard
    SLEET("Sleet"), // All sleet
    FREEZING_RAIN("Freezing Rain"), // Freezing rain/drizzle
    ICE_PELLETS("Ice Pellets"), // Ice pellets
    THUNDERSTORM("Thunderstorm"); // Thunder variants

    private static final Map<Integer, WeatherCondition> BY_CODE = new HashMap<>();

    static {
        BY_CODE.put(1000, CLEAR);

        BY_CODE.put(1003, PARTLY_CLOUDY);
        BY_CODE.put(1006, CLOUDY);
        BY_CODE.put(1009, OVERCAST);

        BY_CODE.put(1030, FOGGY); // Mist
        BY_CODE.put(1135, FOGGY); // Fog
        BY_CODE.put(1147, FOGGY); // Freezing fog

        BY_CODE.put(1150, DRIZZLE); // Patchy light drizzle
        BY_CODE.put(1153, DRIZZLE); // Light drizzle
        BY_CODE.put(1168, DRIZZLE); // Freezing drizzle
        BY_CODE.put(1171, DRIZZLE); // Heavy freezing drizzle

        BY_CODE.put(1063, LIGHT_RAIN); // Patchy rain possible
        BY_CODE.put(1180, LIGHT_RAIN); // Patchy light rain
        BY_CODE.put(1183, LIGHT_RAIN); // Light rain
        BY_CODE.put(1240, LIGHT_RAIN); // Light rain shower

        BY_CODE.put(1186, MODERATE_RAIN); // Moderate rain at times
        BY_CODE.put(1189, MODERATE_RAIN); // Moderate rain

        BY_CODE.put(1192, HEAVY_RAIN); // Heavy rain at times
        BY_CODE.put(1195, HEAVY_RAIN); // Heavy rain
        BY_CODE.put(1243, HEAVY_RAIN); // Moderate or heavy rain shower
        BY_CODE.put(1246, HEAVY_RAIN); // Torrential rain shower

        BY_CODE.put(1066, LIGHT_SNOW); // Patchy snow possible
        BY_CODE.put(1210, LIGHT_SNOW); // Patchy light snow
        BY_CODE.put(1213, LIGHT_SNOW); // Light snow
        BY_CODE.put(1255, LIGHT_SNOW); // Light snow showers

        BY_CODE.put(1216, MODERATE_SNOW); // Patchy moderate snow
        BY_CODE.put(1219, MODERATE_SNOW); // Moderate snow

        BY_CODE.put(1222, HEAVY_SNOW); // Patchy heavy snow
        BY_CODE.put(1225, HEAVY_SNOW); // Heavy snow
        BY_CODE.put(1258, HEAVY_SNOW); // Moderate or heavy snow showers
        BY_CODE.put(1114, HEAVY_SNOW); // Blowing snow
        BY_CODE.put(1117, HEAVY_SNOW); // Blizzard

        BY_CODE.put(1069, SLEET); // Patchy sleet possible
        BY_CODE.put(1204, SLEET); // Light sleet
        BY_CODE.put(1207, SLEET); // Moderate or heavy sleet
        BY_CODE.put(1249, SLEET); // Light sleet showers
        BY_CODE.put(1252, SLEET); // Moderate or heavy sleet showers

        BY_CODE.put(1072, FREEZING_RAIN); // Patchy freezing drizzle possible
        BY_CODE.put(1198, FREEZING_RAIN); // Light freezing rain
        BY_CODE.put(1201, FREEZING_RAIN); // Moderate or heavy freezing rain

        BY_CODE.put(1237, ICE_PELLETS); // Ice pellets
        BY_CODE.put(1261, ICE_PELLETS); // Light showers of ice pellets
        BY_CODE.put(1264, ICE_PELLETS); // Moderate or heavy showers of ice pellets

        BY_CODE.put(1087, THUNDERSTORM); // Thundery outbreaks possible
        BY_CODE.put(1273, THUNDERSTORM); // Patchy light rain with thunder
        BY_CODE.put(1276, THUNDERSTORM); // Moderate or heavy rain with thunder
        BY_CODE.put(1279, THUNDERSTORM); // Patchy light snow with thunder
        BY_CODE.put(1282, THUNDERSTORM); // Moderate or heavy snow with thunder
    }

    public final String name;

    private WeatherCondition(String name) {
        this.name = name;
    }

    public static WeatherCondition fromCode(int code) {
        return BY_CODE.get(code);
    }
}
