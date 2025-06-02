package com.outdeved.alure.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.outdeved.alure.model.weather.ForecastDay;
import com.outdeved.alure.model.weather.MoonPhase;
import com.outdeved.alure.model.weather.Weather;
import com.outdeved.alure.model.weather.WeatherCondition;
import com.outdeved.alure.util.TestUtil;

public class WeatherResponseTest {

    @Test
    public void testWeatherResponse() throws IOException {
        Weather weather = TestUtil.loadWeatherJson("weather/weather.json");

        assertEquals(Month.JULY, weather.getMonth());
        assertEquals(93.9, weather.getTemperature());
        assertEquals(16.1, weather.getWindSpeed());
        assertEquals(29.85, weather.getPressure());
        assertEquals(87.3, weather.getAvgTemp());
        assertEquals(WeatherCondition.PARTLY_CLOUDY, weather.getWeatherCondition());
        assertEquals(MoonPhase.LAST_QUARTER, weather.getMoonPhase());
        assertEquals(36, weather.getMoonIllumination());
        assertEquals(1, weather.getForecastDays().size());
    }

    @Test
    public void testPastWeatherResponse() throws IOException {
        List<ForecastDay> forecastDays = TestUtil.loadPastWeatherJson("weather/pastWeather.json");

        ForecastDay firstDay = forecastDays.get(0);
        assertEquals("2025-05-23", firstDay.getDate().toString());
        assertEquals(51.6, firstDay.getAvgTemp());
        assertEquals(WeatherCondition.LIGHT_RAIN, firstDay.getWeatherCondition());
        assertEquals(MoonPhase.WANING_CRESCENT, firstDay.getMoonPhase());
        assertEquals(22, firstDay.getMoonIllumination());

        ForecastDay secondDay = forecastDays.get(1);
        assertEquals("2025-05-24", secondDay.getDate().toString());
        assertEquals(54.1, secondDay.getAvgTemp());
        assertEquals(WeatherCondition.LIGHT_RAIN, secondDay.getWeatherCondition());
        assertEquals(MoonPhase.WANING_CRESCENT, secondDay.getMoonPhase());
        assertEquals(13, secondDay.getMoonIllumination());

        ForecastDay thirdDay = forecastDays.get(2);
        assertEquals("2025-05-25", thirdDay.getDate().toString());
        assertEquals(59.2, thirdDay.getAvgTemp());
        assertEquals(WeatherCondition.CLOUDY, thirdDay.getWeatherCondition());
        assertEquals(MoonPhase.WANING_CRESCENT, thirdDay.getMoonPhase());
        assertEquals(6, thirdDay.getMoonIllumination());
    }

}
