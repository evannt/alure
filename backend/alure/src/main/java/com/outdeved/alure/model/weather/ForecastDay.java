package com.outdeved.alure.model.weather;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ForecastDay {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private LocalDate date;
    private double avgTemp;
    private WeatherCondition weatherCondition;
    private MoonPhase moonPhase;
    private int moonIllumination;

    public ForecastDay(JsonObject json) {
        this.date = LocalDate.parse(json.get("date").getAsString());

        JsonObject dayJson = json.get("day").getAsJsonObject();
        this.avgTemp = dayJson.get("avgtemp_f").getAsDouble();
        int conditionCode = dayJson.get("condition").getAsJsonObject().get("code").getAsInt();
        this.weatherCondition = WeatherCondition.fromCode(conditionCode);

        JsonObject astronomyJson = json.get("astro").getAsJsonObject();
        this.moonPhase = MoonPhase.fromName(astronomyJson.get("moon_phase").getAsString());
        this.moonIllumination = astronomyJson.get("moon_illumination").getAsInt();
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public MoonPhase getMoonPhase() {
        return moonPhase;
    }

    public int getMoonIllumination() {
        return moonIllumination;
    }

    @Override
    public String toString() {
        return "ForecastDay=[ date=" + date + ", avgTemp=" + avgTemp + ", weatherCondition=" + weatherCondition
                + ", moonPhase=" + moonPhase + ", moonIllumination=" + moonIllumination + " ]";
    }

    public static class ForecastDayDeserializer implements JsonDeserializer<ForecastDay> {

        @Override
        public ForecastDay deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            return new ForecastDay(json.getAsJsonObject());
        }

    }

}
