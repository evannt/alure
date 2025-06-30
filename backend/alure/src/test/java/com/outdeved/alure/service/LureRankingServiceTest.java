package com.outdeved.alure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.outdeved.alure.error.AlureRequestException;
import com.outdeved.alure.error.AlureRequestExceptionType;
import com.outdeved.alure.model.weather.ForecastDay;
import com.outdeved.alure.model.weather.MoonPhase;
import com.outdeved.alure.model.weather.Weather;
import com.outdeved.alure.model.weather.WeatherCondition;
import com.outdeved.alure.util.TestUtil;

@SpringBootTest
public class LureRankingServiceTest {

    @Value("${alure.api.key}")
    private String apiKey;

    @Value("${alure.api.url}")
    private String apiUrl;

    @MockitoBean
    private HttpClient httpClient;

    @Autowired
    private LureRankingService lureRankingService;

    @Test
    public void testFetchWeatherData_ValidLocation() throws IOException, InterruptedException {
        String location = "Long Beach";
        String mockResponseBody = TestUtil.loadJsonString("weather/weather.json");

        HttpResponse<String> httpResponse = createMockResponse(200, mockResponseBody);

        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        Weather weather = lureRankingService.fetchWeatherData(location);
        assertNotNull(weather);
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
    public void testFetchWeatherData_InvalidLocation() throws IOException, InterruptedException {
        String location = "Astronaut";
        String mockResponseBody = TestUtil.loadJsonString("weather/weatherInvalidLocation.json");

        HttpResponse<String> httpResponse = createMockResponse(400, mockResponseBody);

        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND))
                .thenReturn(httpResponse);

        assertThrows(AlureRequestException.class, () -> {
            lureRankingService.fetchWeatherData(location);
        });
    }

    @Test
    public void testFetchPastWeatherData_ValidLocation() throws IOException, InterruptedException {
        String location = "Manhattan";
        String mockResponseBody = TestUtil.loadJsonString("weather/pastWeather.json");

        HttpResponse<String> httpResponse = createMockResponse(200, mockResponseBody);

        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        int hour = 12;
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(3);
        List<ForecastDay> forecastDays = lureRankingService.fetchPastWeatherData(location, hour, startTime, endTime);

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

    @Test
    public void testFetchPastWeatherData_InvalidLocation() throws IOException, InterruptedException {
        String location = "Compiler";
        String mockResponseBody = TestUtil.loadJsonString("weather/weatherInvalidLocation.json");

        HttpResponse<String> httpResponse = createMockResponse(400, mockResponseBody);

        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND))
                .thenReturn(httpResponse);

        int hour = 12;
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(3);
        assertThrows(AlureRequestException.class, () -> {
            lureRankingService.fetchPastWeatherData(location, hour, startTime, endTime);
        });
    }

    @Test
    public void testGetWaterTempEstimate() {
        List<ForecastDay> forecastDays = TestUtil.loadPastWeatherJson("weather/pastWeather.json");
        int waterTempEstimate = lureRankingService.getWaterTempEstimate(forecastDays);
        assertTrue(waterTempEstimate > 0);
        assertTrue(waterTempEstimate < 100);
    }

    @SuppressWarnings("unchecked")
    private HttpResponse<String> createMockResponse(int statusCode, String body) {
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(statusCode);
        when(response.body()).thenReturn(body);
        return response;
    }

}
